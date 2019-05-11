/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol.objects;

import java.io.Serializable;
import java.util.Date;

/**
 * FlightSearch represents the flight search request. User can give
 * departure-arrival airport, departure-arrival date and number of passengers.
 *
 */
public class FlightSearch implements Serializable {

    private String departure_airport, arrival_airport;
    private Date departure_date, arrival_date;
    private int passengers;

    /**
     *
     * @param departure_airport
     * @param arrival_airport
     * @param departure_date
     * @param arrival_date
     * @param passengers
     */
    public FlightSearch(String departure_airport, String arrival_airport,
            Date departure_date, Date arrival_date, int passengers) {
        this.departure_airport = departure_airport;
        this.arrival_airport = arrival_airport;
        this.departure_date = departure_date;
        this.arrival_date = arrival_date;
        this.passengers = passengers;
    }

    /**
     *
     * @return departure_airport
     */
    public String getDepartureAirport() {
        return departure_airport;
    }

    /**
     *
     * @return arrival_airport
     */
    public String getArrivalAirport() {
        return arrival_airport;
    }

    /**
     *
     * @return departure_date
     */
    public Date getDepartureDate() {
        return departure_date;
    }

    /**
     *
     * @return arrival_date
     */
    public Date getArrivalDate() {
        return arrival_date;
    }

    /**
     *
     * @return passengers
     */
    public int getPassengers() {
        return passengers;
    }

    @Override
    public String toString() {
        return "FlightSearch-> departure_airport: " + departure_airport + ", arrival_airport: " + arrival_airport + ", departure_date: " + departure_date
                + ", arrival_date: " + arrival_date + ", passengers: " + passengers;
    }
}
