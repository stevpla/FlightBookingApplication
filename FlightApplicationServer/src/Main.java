
import connections.InitConnections;
import org.apache.log4j.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Main class
 */
public class Main {
    
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        LOGGER.info("FlightApplicationServer starting....");
        // TODO code application logic here
        InitConnections.initRMIProcess();
        //RMI runs on background
    }
}
