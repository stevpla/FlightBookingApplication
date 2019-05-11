/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import protocol.objects.Flight;
import protocol.objects.FlightReservation;
import protocol.objects.FlightSearch;
import protocol.objects.Flights;
import protocol.objects.FlightsEntry;
import utils.DBServerInfo;

/**
 *
 * 
 */
public class DBCommunication {
    private static final Logger LOGGER = Logger.getLogger(DBCommunication.class.getName());
    
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    
    /**
     *
     * @param fs
     * @return
     */
    public List<Flight> searchFlightInDB(FlightSearch fs){
        LOGGER.info("Starting searchFlightInDB(..)");
        
        try{
            socket = new Socket(DBServerInfo.getDBServerIP(), DBServerInfo.getDBServerPort());
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            //send flight search info to dbserver
            oos.writeObject(fs);
            //read results
            Object ob = ois.readObject();
            if(ob instanceof Flights){
                if(((Flights) ob).getFlag()){
                    return ((Flights) ob).getFlights();
                }else{
                    return new ArrayList<Flight>();
                }
            }else{
                LOGGER.warn("Format error. Class Flights object expected.");
                return new ArrayList<Flight>();
            }
        }catch(IOException | ClassNotFoundException exc){
            LOGGER.error(exc.toString());
        }
        return new ArrayList<Flight>();
    }
    
    /**
     *
     * @param fr
     * @return
     */
    public boolean updateFlightInDB(FlightReservation fr){
        LOGGER.info("Starting updateFlightInDB(..)");
        
        try{
            socket = new Socket(DBServerInfo.getDBServerIP(), DBServerInfo.getDBServerPort());
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            //send flight reservation to dbserver
            oos.writeObject(fr);
            //read results
            Object ob = ois.readObject();
            if(ob instanceof FlightsEntry){
                if(((FlightsEntry) ob).getMessage()){
                    return true;
                }else{
                    return false;
                }
            }else{
                LOGGER.warn("Format error. Class FlightsEntry object expected.");
                return false;
            }
        }catch(IOException | ClassNotFoundException exc){
            LOGGER.error(exc.toString());
        }
        return false;
    }
}
