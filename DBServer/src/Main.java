/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import connections.InitializeConnection;
import org.apache.log4j.Logger;

/**
 * The class Main represents the start class. Contains static main method.
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    /**
     * Method which initial java process start execution
     *
     * @param args
     */
    public static void main(String[] args) {
        LOGGER.info("Starting DBServer application.......");
        InitializeConnection.initSocketConn();
    }
}
