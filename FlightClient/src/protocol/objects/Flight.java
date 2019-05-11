/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol.objects;

import java.io.Serializable;
import java.util.Date;

/**
 * This class represents a Flight. But a Flight in the table Flights has an
 * extra column-field, time. Here date and time encapsulated into field date.
 *
 */
public class Flight implements Serializable {

    private Date date; //in-time
    private String departure_airport, arrival_airport;
    private String code;
    private int seats;
    private float price;

    /**
     *
     * @param date
     * @param departure_airport
     * @param arrival_airport
     * @param code
     * @param seats
     * @param price
     */
    public Flight(Date date, String departure_airport, String arrival_airport,
            String code, int seats, float price) {
        this.date = date;
        this.departure_airport = departure_airport;
        this.arrival_airport = arrival_airport;
        this.code = code;
        this.seats = seats;
        this.price = price;
    }

    /**
     *
     * @return date
     */
    public Date getDate() {
        return date;
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
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @return seats
     */
    public int getSeats() {
        return seats;
    }

    /**
     *
     * @return price
     */
    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Flight-> " + "From: " + departure_airport + ", To: " + arrival_airport
                + ", Date: " + date + ", Flight code: " + code + ", Seats: " + seats
                + ", Price: " + price;
    }
}
