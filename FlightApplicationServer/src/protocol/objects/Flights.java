/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol.objects;

import java.io.Serializable;
import java.util.List;

/**
 * This class represents the answer to the Flight search request. Gathers all
 * flights matching user criteria. But in case that there are no available
 * flights, then a flag is set to false.
 *
 */
public class Flights implements Serializable {

    private List<Flight> list;
    private boolean flag;

    /**
     *
     * @param list
     * @param flag
     */
    public Flights(List<Flight> list, boolean flag) {
        this.list = list;
        this.flag = flag;
    }

    /**
     *
     * @return List of Flight
     */
    public List<Flight> getFlights() {
        return list;
    }

    /**
     *
     * @return flag
     */
    public boolean getFlag() {
        return flag;
    }

    @Override
    public String toString() {
        return "Flights -> Flights List: " + list.toArray().toString() + ", flag: " + flag;
    }
}
