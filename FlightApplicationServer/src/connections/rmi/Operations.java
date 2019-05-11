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
 * Remote interface
 */
public interface Operations extends Remote{

    /**
     *
     * @param fs
     * @return 
     * @throws RemoteException
     */
    public List<Flight> searchFlight(FlightSearch fs) throws RemoteException;
    
    /**
     *
     * @param fs
     * @return 
     * @throws RemoteException
     */
    public boolean reservateFlight(FlightReservation fs) throws RemoteException;
}
