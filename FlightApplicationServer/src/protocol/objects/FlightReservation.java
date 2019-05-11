/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol.objects;

import java.io.Serializable;

/**
 * This class represents the request of flight reservation.
 *
 */
public class FlightReservation implements Serializable {

    private Flight a, b;
    private int passengers;

    /**
     *
     * @param a Flight a
     * @param b Flight b
     * @param passengers
     */
    public FlightReservation(Flight a, Flight b, int passengers) {
        this.a = a;
        this.b = b;
        this.passengers = passengers;
    }

    /**
     *
     * @return Flight a
     */
    public Flight getFlightA() {
        return a;
    }

    /**
     *
     * @return Flight b
     */
    public Flight getFlightB() {
        return b;
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
        return "FlightReservation -> Flight A: " + a.toString() + ",  Flight B: " + b.toString() + ", passengers: " + passengers;

    }
}
