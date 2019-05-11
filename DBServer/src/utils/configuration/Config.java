/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;
import utils.ServerSecurity;

/**
 * Config class offers the functionality of reading properties file.
 *
 */
public class Config {

    private static final Logger LOGGER = Logger.getLogger(Config.class.getName());

    /**
     * Reads .properties and extract port numbers
     *
     * @return int port number
     * @throws IOException
     */
    public static int getProperties() throws IOException, NumberFormatException {
        LOGGER.info("Start getProperties()");
        InputStream input = new FileInputStream("dbserver.properties");

        Properties prop = new Properties();
        // load a properties file
        prop.load(input);

        //Load FlightServer ip
        ServerSecurity.setFlightServerIp(prop.getProperty("application_server.ip"));

        // get the property value
        return Integer.parseInt(prop.getProperty("port"));
    }
}
