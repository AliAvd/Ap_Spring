package Model;

import java.io.Serial;
import java.io.Serializable;

/**
 * Flight class ...
 */
public class Flight extends subscription implements Serializable {
    @Serial
    private static final long serialVersionUID = 4113799434508676095L;
    private String airplane_name;
    private int ticket_price;

    /**
     * constructor of Flight class ...
     * @param date
     * @param origin
     * @param destination
     * @param name
     * @param ticket_price
     * @param capacity
     */
    public Flight(String date, String origin, String destination,String name,int ticket_price,int capacity) {
        super(date, origin, destination,capacity);
        this.airplane_name = name;
        this.ticket_price = ticket_price;
    }

    /**
     * getting flights airplane name ...
     * @return
     */

    public String getAirplane_name() {
        return airplane_name;
    }

    /**
     * getting ticket price of a flight ...
     * @return
     */
    public int getTicket_price() {
        return ticket_price;
    }

}
