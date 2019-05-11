/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.configration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * Config class offers the properties loading functionality.
 */
public class Config {

    private static final Logger LOGGER = Logger.getLogger(Config.class.getName());

    /**
     * Reads .properties and extract domain
     *
     * @return int port number
     * @throws IOException
     */
    public static String getProperties() throws IOException, NumberFormatException {
        LOGGER.info("Start getProperties()");
        InputStream input = new FileInputStream("client.properties");

        Properties prop = new Properties();
        // load a properties file
        prop.load(input);

        // get the property value
        return prop.getProperty("domain");
    }
}
