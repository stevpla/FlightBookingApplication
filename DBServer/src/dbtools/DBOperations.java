/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbtools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import protocol.objects.Flight;
import protocol.objects.FlightReservation;

/**
 * Class DBOperations offers basic methods for interacting with the MySQL data
 * base. Operations of select, update a table, and use a data base are
 * implemented.
 *
 */
public class DBOperations {

    private static final Logger LOGGER = Logger.getLogger(DBOperations.class.getName());

    //Date formatters
    //Date must be converted into a String form in order to search into Table
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd"),
            SDF_TIME = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /**
     * A check is happened with two select operations in Flights table. To cross
     * the information that the two flights have number of seats equal or
     * greater than user passengers.
     *
     * @param db_conn Data base connection
     * @param fr FlightReservation object to get information
     * @return True if the two flights preserves indeed seats equal or greater than passengers.
     * Otherwise false.
     */
    public static boolean selectMySQLTableFlightForUpdate(Connection db_conn, FlightReservation fr) {
        LOGGER.info("selectMySQLTableFlightForUpdate(..) starting.");
        boolean a = false, b = false;

        try {
            //1. select first flight
            String select_sql_a = "SELECT * FROM Flights WHERE flight_code='" + fr.getFlightA().getCode() + "'";
            PreparedStatement prepStmt = null;
            prepStmt = db_conn.prepareStatement(select_sql_a);
            ResultSet rs = prepStmt.executeQuery();
            LOGGER.info(select_sql_a);
            while (rs.next()) {
                //extract seats column and compare
                int seats = rs.getInt("seats");
                if (seats >= fr.getFlightA().getSeats()) {
                    a = true;
                }
            }

            //2. select second flight
            String select_sql_b = "SELECT * FROM Flights WHERE flight_code='" + fr.getFlightB().getCode() + "'";
            prepStmt = null;
            prepStmt = db_conn.prepareStatement(select_sql_b);
            rs = null;
            rs = prepStmt.executeQuery();
            LOGGER.info(select_sql_b);
            while (rs.next()) {
                //extract seats column and compare
                int seats = rs.getInt("seats");
                if (seats >= fr.getFlightB().getSeats()) {
                    b = true;
                }
            }
            //return a and b values.
            return a&b;
        } catch (SQLException sql) {
            LOGGER.error(sql.toString());
        }
        return false;
    }

    /**
     * Search the two flights from user criteria applying two select into table.
     *
     * @param db_conn DataBase Connection
     * @param from departure airport
     * @param to arrival airport
     * @param flight_date flight date
     * @param passengers number of seats user wants
     * @return Return List of Flight objects filled with real Flight objects in
     * case that Flights can be found according to user criteria. Otherwise
     * return an empty List of Flight objects.
     */
    public static List<Flight> selectMySQLTableFlightForSeach(Connection db_conn, String from, String to, java.util.Date flight_date, int passengers) {
        LOGGER.info("selectMySQLTableFlightForSeach(..) starting.");
        List<Flight> list = new ArrayList<Flight>();

        try {
            String select_sql = "SELECT * FROM Flights WHERE departure_airport='" + from + "'"
                    + " AND arrival_airport='" + to + "'" + " AND fdate='" + SDF.format(flight_date) + "'"
                    + " AND seats >= " + passengers;
            PreparedStatement prepStmt = null;
            prepStmt = db_conn.prepareStatement(select_sql);
            ResultSet rs = prepStmt.executeQuery();
            LOGGER.info(select_sql);
            if (rs.next() == false) {
                return new ArrayList<Flight>();
            } else {
                do {
                    java.sql.Date date = rs.getDate("fdate");
                    java.sql.Time time = rs.getTime("ftime");

                    java.util.Date op = null;
                    try {
                        op = SDF_TIME.parse(date.toString() + " " + time.toString());
                    } catch (ParseException pe) {
                        LOGGER.error(pe.toString());
                    }
                    //Save to list
                    //Construct the Flight object
                    list.add(new Flight(op, rs.getString("departure_airport"),
                            rs.getString("arrival_airport"), rs.getString("flight_code"), rs.getInt("seats"),
                            rs.getFloat("price")));
                } while (rs.next());
                //return list with all flights available.
                return list;
            }
        } catch (SQLException sql) {
            LOGGER.error(sql.toString());
        }
        return list;
    }

    /**
     * Use flight_sys MySQL Database.
     *
     * @param db_conn Database Connection
     */
    public static void useMySQLDataBase(Connection db_conn) {
        LOGGER.info("useMySQLDataBase(..) starting.");
        try {
            //USE flight_sys DATABASE;
            String use_db_sql = "use flight_sys";
            PreparedStatement prepStmt = null;
            prepStmt = db_conn.prepareStatement(use_db_sql);
            prepStmt.executeQuery();
            LOGGER.info(use_db_sql);
        } catch (SQLException sql) {
            LOGGER.error(sql.toString());
        }
    }

    /**
     * To complete a flight reservate request, two update actions must occured.
     * Column seats of these two flights decreased by passenger number.
     *
     * @param db_conn Database connection
     * @param fr FlightReservation
     * @return True if the two update actions can be completed. False otherwise.
     */
    public static boolean updateMySQLTableFlight(Connection db_conn, FlightReservation fr) {
        LOGGER.info("updateMySQLTableFlight(..) starting.");
        try {
            //1.update first flight seats COLUMN
            String update_first_flight_sql = "UPDATE Flights SET seats=seats - " + fr.getPassengers() + " WHERE flight_code='" + fr.getFlightA().getCode() + "'";
            PreparedStatement prepStmt = null;
            prepStmt = db_conn.prepareStatement(update_first_flight_sql);
            prepStmt.executeUpdate();
            LOGGER.info(update_first_flight_sql);

            //2. update second flight seats COLUMN
            String update_second_flight_sql = "UPDATE Flights SET seats=seats - " + fr.getPassengers() + " WHERE flight_code='" + fr.getFlightB().getCode() + "'";
            prepStmt = null;
            prepStmt = db_conn.prepareStatement(update_second_flight_sql);
            prepStmt.executeUpdate();
            LOGGER.info(update_second_flight_sql);
            //return in this point
            return true;
        } catch (SQLException sql) {
            LOGGER.error(sql.toString());
        }
        return false;
    }
}
