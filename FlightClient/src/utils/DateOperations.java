/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * DateOperations provides functions such as convert date to string, and compare
 * dates.
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

public class DateOperations {

    private static final Logger LOGGER = Logger.getLogger(DateOperations.class.getName());

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * convertStringToDate, string representation to date.
     *
     * @param date
     * @return new date
     */
    public static Date convertStringToDate(String date) {
        try {
            return FORMATTER.parse(date);
        } catch (ParseException pe) {
            LOGGER.error(pe.toString());
        }
        return null;
    }

    /**
     * Compare 2 dates.
     *
     * @param da
     * @param db
     * @return True if da is greater than db. False, otherwise.
     */
    public static boolean compareDates(Date da, Date db) {
        return db.after(da);
    }
}
