/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbtools.connections;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * DBConnection represents the database connection hanlder. In case some class
 * needs a db connection. It is the proxy for the class DBConnectionsPool.
 *
 */
public class DBConnection {

    private static BasicDataSource BASICDC = DBConnectionsPool.getInstance().getBasicDS();

    /**
     * Offers a DB connection
     *
     * @return Connection object that handles mysql connection
     * @throws java.sql.SQLException
     */
    public static Connection getDBConnection() throws SQLException {
        return BASICDC.getConnection();
    }
}
