/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocol.objects;

import java.io.Serializable;

/**
 * This class represents the answer to flight reservation request. A message is
 * set to true or false instead.
 *
 */
public class FlightsEntry implements Serializable {

    private boolean message;

    /**
     *
     * @param message True or false
     */
    public FlightsEntry(boolean message) {
        this.message = message;
    }

    /**
     *
     * @return message
     */
    public boolean getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "FlightsEntry -> message: " + message;
    }
}
