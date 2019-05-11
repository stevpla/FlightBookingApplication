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
import utils.DBServerInfo;

/**
 *
 * 
 */
public class Config {
    
    private static final Logger LOGGER = Logger.getLogger(Config.class.getName());
    
    private static String DOMAIN;
    private static int PORT;

    /**
     * Reads .properties and extract domain 
     *
     * @throws IOException
     */
    public static void getProperties() throws IOException, NumberFormatException {
        LOGGER.info("Start getProperties()");
        InputStream input = new FileInputStream("flight_application_server.properties");

        Properties prop = new Properties();
        // load a properties file
        prop.load(input);
        
        //Load ip-port of dbserver
        DBServerInfo.setIpPort(prop.getProperty("dbserver.ip"), Integer.parseInt(prop.getProperty("dbserver.port")));

        // get the property value
        DOMAIN = prop.getProperty("domain");
        PORT = Integer.parseInt(prop.getProperty("port"));
    }
    
    public static String getDomain(){
        return DOMAIN;
    }
    
    public static int getPort(){
        return PORT;
    }
}
