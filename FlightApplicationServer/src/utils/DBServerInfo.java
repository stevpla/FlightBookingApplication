/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * 
 */
public class DBServerInfo {
    private static String DBSERVER_IP;
            
    private static int DBSERVER_PORT;
    
    /**
     *
     * @param ip
     * @param port
     */
    public static void setIpPort(String ip, int port){
        DBSERVER_IP = ip;
        DBSERVER_PORT = port;
    }
    
    /**
     *
     * @return
     */
    public static String getDBServerIP(){
        return DBSERVER_IP;
    }
    
    /**
     *
     * @return
     */
    public static int getDBServerPort(){
        return DBSERVER_PORT;
    }
}
