/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbtools.connections;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * This class handles the database connections pool. It creates a data source
 * representing mysql and return a db connection on demand.
 *
 */
public class DBConnectionsPool {

    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/flight_sys";
    private static final String DB_USER = "fuser";
    private static final String DB_PWD = "flight";

    private static DBConnectionsPool DS;
    private BasicDataSource basicDS = new BasicDataSource();

    //private constructor
    private DBConnectionsPool() {
        //BasicDataSource basicDS = new BasicDataSource();
        basicDS.setDriverClassName(DRIVER_CLASS);
        basicDS.setUrl(DB_CONNECTION_URL);
        basicDS.setUsername(DB_USER);
        basicDS.setPassword(DB_PWD);

        // Parameters for connection pooling
        //basicDS.setInitialSize(10);
        //basicDS.setMaxTotal(10);
    }

    /**
     * Initialize the BasicDataSource object.
     *
     * @return DBConnectionsPool Object
     */
    public static DBConnectionsPool getInstance() {
        if (DS == null) {
            DS = new DBConnectionsPool();
        }
        return DS;
    }

    /**
     * Return the BasicDataSource object for retrieving connections.
     *
     * @return BasicDataSource object.
     */
    public BasicDataSource getBasicDS() {
        return basicDS;
    }
}
