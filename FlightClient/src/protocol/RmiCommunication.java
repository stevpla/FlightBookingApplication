/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol;

import connections.RmiDomain;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import protocol.objects.Flight;
import protocol.objects.FlightReservation;
import protocol.objects.FlightSearch;

/**
 *
 * RmiCommunication gathers two remote methods actual implementation.
 */
public class RmiCommunication {

    private static final Logger LOGGER = Logger.getLogger(RmiCommunication.class.getName());

    /**
     * searchFlights contains the actual logic of remote method.
     *
     * @param fs
     * @return List of flights. Otherwise an empty list is returned.
     */
    public static List<Flight> searchFlights(FlightSearch fs) {
        try {
            LOGGER.info("Invoking remote method: searchFlight");
            return RmiDomain.getRemoteObjectReference().searchFlight(fs);
        } catch (RemoteException re) {
            LOGGER.error(re.toString());
            JOptionPane.showMessageDialog(null, "Remote method could not be invoked.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return new ArrayList<Flight>();
    }

    /**
     * updateFlights contains the actual logic of remote method.
     *
     * @param fr
     * @return True if two flights are updated!. False, otherwise.
     */
    public static boolean updateFlights(FlightReservation fr) {
        try {
            LOGGER.info("Invoking remote method: reservateFlight");
            return RmiDomain.getRemoteObjectReference().reservateFlight(fr);
        } catch (RemoteException re) {
            LOGGER.error(re.toString());
            JOptionPane.showMessageDialog(null, "Remote method could not be invoked.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
