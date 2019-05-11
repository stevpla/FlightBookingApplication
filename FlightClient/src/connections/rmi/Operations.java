/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connections.rmi;

import java.rmi.*;
import java.util.List;
import protocol.objects.Flight;
import protocol.objects.FlightReservation;
import protocol.objects.FlightSearch;

/**
 *
 * Remote interface supplied by RMI Server
 */
public interface Operations extends Remote {

    /**
     * search method
     *
     * @param fs
     * @return List of flights
     * @throws RemoteException
     */
    public List<Flight> searchFlight(FlightSearch fs) throws RemoteException;

    /**
     * reservate flights method
     *
     * @param fs
     * @return True if flights successfully updated. False, otherwise.
     * @throws RemoteException
     */
    public boolean reservateFlight(FlightReservation fs) throws RemoteException;
}
