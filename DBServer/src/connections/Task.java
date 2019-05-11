/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connections;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import org.apache.log4j.Logger;
import protocol.FlightActions;

/**
 *
 * The class Task, represents the logic of a Client when arrives at server. Is
 * the key point between InitializeConnection and ThreadPool classes. So, when a
 * client arrives, a Task object is being created with client socket and is
 * being passed to ThreadPool for starting a new thread and executing Task run
 * method as a separate thread.
 *
 */
public class Task implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(Task.class.getName());

    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    /**
     * Creates a new Task initializing socket, and i/o object streams.
     *
     * @param socket Client Socket
     * @param oos Client output object stream
     * @param ois Client input object stream
     */
    public Task(Socket socket, ObjectOutputStream oos, ObjectInputStream ois) {
        this.socket = socket;
        this.oos = oos;
        this.ois = ois;
    }

    @Override
    public void run() {
        try {
            //If violation occures, then close connection, and move on to next conn
            new FlightActions().doFlightProtocol(socket, oos, ois);
            //Move on to next conn
            LOGGER.info("Connection finished!!");
        } catch (IOException | ClassNotFoundException ex) {
            LOGGER.error(ex.toString());
            try {
                socket.close();
                oos.close();
                ois.close();
            } catch (IOException ioe) {
                LOGGER.error("At closing streams..");
            }
            LOGGER.warn("This connection is failed..Move on!");
        }
    }
}
