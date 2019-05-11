/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol;

import dbtools.DBOperations;
import dbtools.connections.DBConnection;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import protocol.objects.Flight;
import protocol.objects.FlightReservation;
import protocol.objects.FlightSearch;
import protocol.objects.Flights;
import protocol.objects.FlightsEntry;

/**
 * This class implements the protocol and the synchronized methods which provide
 * interaction with the MySQL. SELECT/SELECT can be acceptable in terms of
 * multithreaded performance. But, SELECT/UPDATE and UPDATE/UPDATE can be lead
 * into disaster.
 *
 *
 */
public class FlightActions {

    private static final Logger LOGGER = Logger.getLogger(FlightActions.class.getName());

    private static volatile boolean FLAG_A = false, FLAG_B = true;
    //private static volatile boolean FLAG = false;

    /**
     *
     * @param socket Client socket
     * @param oos Client output object stream
     * @param ois Client input object stream
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public void doFlightProtocol(Socket socket, ObjectOutputStream oos, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        //Protocol begins
        Object request = ois.readObject();
        if (request instanceof FlightSearch) {
            //Then is a SEARCH request.
            List<Flight> list = searchFlight(socket, oos, ois, ((FlightSearch) request));
            if (!list.isEmpty()) {
                //send flights list to Application Server
                oos.writeObject(new Flights(list, true));
            } else {
                //send flights list to Application Server
                //With false mode. So there is no flights at all.
                oos.writeObject(new Flights(list, false));
            }
        } else if (request instanceof FlightReservation) {
            //Then, is an update request
            if (updateFlight(socket, oos, ois, ((FlightReservation) request))) {
                //send true back
                oos.writeObject(new FlightsEntry(true));
            } else {
                oos.writeObject(new FlightsEntry(false));
            }
        } else {
            throw new IOException("Not expected protocol objects format and content..");
        }
        //Close streams
        socket.close();
        oos.close();
        ois.close();
    }

    /**
     * This method has to be in a exclusive mode. Only one connection can invoke
     * this method a time. Therefore each thread must wait before acquire a
     * lock. But, select action with another or n others select actions can be
     * run same time without crazy results. SELECT/SELECT is allowed.
     * SELECT/UPDATE is forbidden\ and UPDATE/UPDATE is forbidden also.
     *
     * @param socket
     * @param oos
     * @param ois
     * @param fs
     * @return List of Flight objects if the flights can be found into table
     * matching user criteria. Otherwise an empty List returned.
     */
    public synchronized List<Flight> searchFlight(Socket socket, ObjectOutputStream oos, ObjectInputStream ois, FlightSearch fs) {
        //Request MySQL Connection to do the search
        //Serial tasks
        //1. Search for source -> destination
        //2. If yes, then search destination -> source flights
        //3. Pack them into List
        //4. send them
        while (FLAG_B == false) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        //lock this area
        //No update. Allow only multiple select's
        FLAG_A = true;

        try {
            //Get a MySQL connection
            //--------------------------------------------------------------------------------------------------------------
            Connection db_connection = DBConnection.getDBConnection();

            DBOperations.useMySQLDataBase(db_connection);
            //Then do the 2 select operations
            List<Flight> list_a = DBOperations.selectMySQLTableFlightForSeach(db_connection, fs.getDepartureAirport(),
                    fs.getArrivalAirport(), fs.getDepartureDate(), fs.getPassengers());
            //switch FROM -> TO, to TO -> FROM
            List<Flight> list_b = DBOperations.selectMySQLTableFlightForSeach(db_connection, fs.getArrivalAirport(),
                    fs.getDepartureAirport(), fs.getArrivalDate(), fs.getPassengers());

            db_connection.close();
            //----------------------------------------------------------------------------------------------------------------

            //Check lists
            if (list_a.isEmpty()) {
                FLAG_A = false;
                notifyAll();
                return new ArrayList<Flight>();
            } else {
                if (!list_b.isEmpty()) {
                    //merge them
                    list_a.addAll(list_b);
                    FLAG_A = false;
                    notifyAll();
                    return list_a;
                } else {
                    FLAG_A = false;
                    notifyAll();
                    return new ArrayList<Flight>();
                }
            }
        } catch (SQLException sql) {
            LOGGER.error(sql.toString());
        }
        return new ArrayList<Flight>();
    }

    /**
     * This method has to be in a exclusive mode. Only one connection can invoke
     * this method a time. Therefore each thread must wait before acquire a
     * lock.
     *
     * @param socket
     * @param oos
     * @param ois
     * @param fr
     * @return True if the two updates can be completed into table. False,
     * otherwise.
     */
    public synchronized boolean updateFlight(Socket socket, ObjectOutputStream oos, ObjectInputStream ois, FlightReservation fr) {
        //Request MySQL Connection to do the update
        //Serial tasks
        //First take the LOCK AND
        //SEE THESE FLIGHTS WITH A SELECT and then
        //1. Update first flight
        //2. Update second flight
        while (FLAG_A == true) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }

        FLAG_A = true;
        //And lock all SELECT operations
        FLAG_B = false;

        try {
            //Get a MySQL connection
            Connection db_connection = DBConnection.getDBConnection();

            DBOperations.useMySQLDataBase(db_connection);
            if (DBOperations.selectMySQLTableFlightForUpdate(db_connection, fr)) {
                //The 2 flights can support client seats
                if (DBOperations.updateMySQLTableFlight(db_connection, fr)) {
                    db_connection.close();
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (SQLException sql) {
            LOGGER.error(sql.toString());
        }
        return false;
    }
}
