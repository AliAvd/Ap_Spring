package Model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Ariplane class for keeping airplane attributes ...
 */
public class Airplane implements Serializable {
    @Serial
    private static final long serialVersionUID = 2113799434508676095L;
    private String name;
    private int capacity;
    private ArrayList<Flight> Flights = new ArrayList<>();

    /**
     * Airplane class constructor ...
     * @param name
     * @param capacity
     */
    public Airplane(String name,int capacity) {
        this.capacity = capacity;
        this.name = name;

    }

    /**
     * getting airplanes capacity because it is a private field ...
     * @return
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * getting aiplane corresponding flights because it is a private field ...
     * @return
     */

    public ArrayList<Flight> getFlights() {
        return Flights;
    }

    /**
     * gettin airplane name because it is a private field ...
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * adding a flight to airplane list of flights ...
     * @param flight
     */


    public void addflight(Flight flight){
        Flights.add(flight);
    }
}
