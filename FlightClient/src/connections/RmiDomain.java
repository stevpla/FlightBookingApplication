/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connections;

import connections.rmi.Operations;
import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.log4j.Logger;
import utils.configration.Config;
import java.rmi.*;
import javax.swing.JOptionPane;

/**
 * The class RmiDomain represents the RMI initialization process. Reading
 * properties file and looking up the rmi serve domain.
 */
public class RmiDomain {

    private static final Logger LOGGER = Logger.getLogger(RmiDomain.class.getName());

    private static Operations LOOK_UP = null;

    /**
     * Look up domain and hold remote reference
     */
    public static void lookUpRmiDomain() {
        String domain = null;

        try {
            domain = Config.getProperties();
        } catch (IOException | NumberFormatException exc) {
            //Missing file
            //Or empty domain
            LOGGER.error(exc.toString());
            LOGGER.warn("Flight Client shuted down.....");
            JOptionPane.showMessageDialog(null, exc.getLocalizedMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }

        //Look up domain and keep the reference to remote object
        try {
            LOOK_UP = (Operations) Naming.lookup(domain);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            LOGGER.error(ex.toString());
            LOGGER.warn("Flight Client shuted down.....");
            JOptionPane.showMessageDialog(null, "Could not connect Flight Server.Please try later.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

    /**
     * Return remote object reference for invoking remote methods.
     *
     * @return remote object reference
     */
    public static Operations getRemoteObjectReference() {
        return LOOK_UP;
    }
}
