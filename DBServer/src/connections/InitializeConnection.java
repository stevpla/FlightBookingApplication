/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connections;

import connections.pool.ThreadPool;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import org.apache.log4j.Logger;
import utils.ServerSecurity;
import utils.configuration.Config;

/**
 * The class InitializeConnection represents the initialization of DBServer.
 * Also, functionalities such as reading properties are added. A new thread is
 * started per new client with the help of ThreadPool.
 *
 */
public class InitializeConnection {

    private static final Logger LOGGER = Logger.getLogger(InitializeConnection.class.getName());

    private static ServerSocket SERVER_SOCKET;
    private static Socket SOCKET;

    private static ObjectInputStream OIS;
    private static ObjectOutputStream OOS;

    /**
     * Covers reading properties file, initialization of server socket and
     * accepting a client connection
     */
    public static void initSocketConn() {
        //First, read port from properties
        int port = 0;
        try {
            port = Config.getProperties();
        } catch (IOException | NumberFormatException ex) {
            //Missing file
            //Or empty port
            LOGGER.error(ex.toString());
            LOGGER.warn("DBServer shuted down.....");
            System.exit(-1);
        }

        //Then create server socket for listening clients..
        try {
            SERVER_SOCKET = new ServerSocket(port);
            LOGGER.info("DBServer is waiting for connections.. at port:" + port);
        } catch (IOException ioe) {
            //Port is already in USE.  
            LOGGER.error(ioe.toString() + " port: " + port);
            LOGGER.warn("DBServer shuted down.....");
            System.exit(-1);
        }

        //Finally, run for ever and accept clients
        while (true) {
            try {
                SOCKET = null;
                OOS = null;
                OIS = null;
                LOGGER.info("Waiting for connection..");
                SOCKET = SERVER_SOCKET.accept();
                //Security first
                if (isFlightServerIpILegal(SOCKET)) {
                    LOGGER.info("Connection established with IP: " + SOCKET.getInetAddress() + ", Port: " + SOCKET.getPort());
                    //Object Streams
                    OIS = new ObjectInputStream(SOCKET.getInputStream());
                    OOS = new ObjectOutputStream(SOCKET.getOutputStream());
                    //Use new thread from pool as needed
                    ThreadPool.useCachedThread(new Task(SOCKET, OOS, OIS));
                } else {
                    LOGGER.warn(SOCKET.getInetAddress().toString() + " is trying to connect. Possible Security Attack!");
                    LOGGER.info("Terminate this connection immidiately.");
                    SOCKET.close();
                }
            } catch (IOException ioe) {
                LOGGER.error(ioe.toString());
                LOGGER.warn("This connection is failed..Move on!");
            }
        }
    }

    private static boolean isFlightServerIpILegal(Socket sock) {
        return sock.getInetAddress().getHostAddress().equals(ServerSecurity.getFlightServerIp());
    }
}
