/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connections.rmi;

import java.rmi.*;
import java.rmi.server.*;
import java.util.List;
import org.apache.log4j.Logger;
import protocol.DBCommunication;
import protocol.objects.Flight;
import protocol.objects.FlightReservation;
import protocol.objects.FlightSearch;

/**
 *
 * This class implements remote methods and creates and exports a remote object.
 */
public class OperationServer extends UnicastRemoteObject implements Operations{
    
    private static final Logger LOGGER = Logger.getLogger(OperationServer.class.getName());

    /**
     *
     * @throws RemoteException
     */
    public OperationServer ()throws RemoteException{
        super();
    }
    
    @Override
    public List<Flight> searchFlight(FlightSearch fs) throws RemoteException {
        try {
            LOGGER.info("searchFlight invoked!");
            LOGGER.info("Incoming client: " + getClientHost());
        } catch (ServerNotActiveException snae) {
            LOGGER.error(snae.toString());
        }
        //Start DBServer protocol
        return new DBCommunication().searchFlightInDB(fs);
    }

    @Override
    public boolean reservateFlight(FlightReservation fr) throws RemoteException {
        try {
            LOGGER.info("reservateFlight invoked!");
            LOGGER.info("Incoming client: " + getClientHost());
        } catch (ServerNotActiveException snae) {
            LOGGER.error(snae.toString());
        }
        //Start DBServer protocol
        return new DBCommunication().updateFlightInDB(fr);
    }
}
