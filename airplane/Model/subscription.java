package Model;

import java.io.Serial;
import java.io.Serializable;

/**
 * subscription class for keeping some common attributes ...
 */
public class subscription implements Serializable {
    @Serial
    private static final long serialVersionUID = 5113799434508676095L;
    private String date;
    private String origin;
    private String destination;
    private int capacity;

    /**
     * getter for capacity ...
     * @return
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * setter for capacity ...
     * @param capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * getter flight date ...
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * getter for flights origin ...
     * @return
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * getter for flights destination ...
          * @return
     */
    public String getDestination() {
        return destination;
    }

    /**
     * constructor of subscription class ...
     * @param date
     * @param origin
     * @param destination
     * @param capacity
     */
    public subscription(String date, String origin, String destination,int capacity) {
        this.date = date;
        this.origin = origin;
        this.destination = destination;
        this.capacity = capacity;
    }
}
