/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 * Holds rmi server ip
 *
 */
public class ServerSecurity {
    
    private static String FLIGHT_APPLICATION_SERVER_IP;
    
    /**
     *
     * @param ip
     */
    public static void setFlightServerIp(String ip){
        FLIGHT_APPLICATION_SERVER_IP = ip;
    }
    
    /**
     *
     * @return
     */
    public static String getFlightServerIp(){
        return FLIGHT_APPLICATION_SERVER_IP;
    }
}
