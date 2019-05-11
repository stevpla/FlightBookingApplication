/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connections;

import java.rmi.*;
import connections.rmi.OperationServer;
import java.io.IOException;
import java.net.MalformedURLException;
import org.apache.log4j.Logger;
import utils.configuration.Config;

/**
 *
 * Starts rmi name binding and registry
 */
public class InitConnections {
    
    private static final Logger LOGGER = Logger.getLogger(InitConnections.class.getName());
    
    /**
     *
     */
    public static void initRMIProcess() {
        //read config file
        String domain = null;
        int port = 0;
        
        try {
            Config.getProperties();
            domain = Config.getDomain();
            port = Config.getPort();
        } catch (IOException | NumberFormatException exc) {
            //Missing file
            //Or empty port
            //Or empty domain
            LOGGER.error(exc.toString());
            LOGGER.warn("FlightApplicationServer shuted down.....");
            System.exit(-1);
        }
        
        try {
            //RMISecurityManager security = new RMISecurityManager();
            // System.setSecurityManager(security);
            OperationServer os = new OperationServer();
            java.rmi.registry.LocateRegistry.createRegistry(port);
            //1099 is the port number
            Naming.rebind(domain, os);
            LOGGER.info("FlightApplicationServer listens on " + domain);
        } catch (MalformedURLException | RemoteException exc) {
            LOGGER.error(exc.toString());
            LOGGER.warn("FlightApplicationServer shuted down.....");
            System.exit(-1);
        }
    }
}
