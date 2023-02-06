package Model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Customer class to keeping customer attributes ...
 */
public class Customer implements Serializable {
    @Serial
    private static final long serialVersionUID = 3113799434508676095L;
    private String username;
    private String password;
    private int budget;
    private ArrayList<Flight> Flights = new ArrayList<>();

    /**
     * getting customer budget because it is a private field ...
     * @return
     */
    public int getBudget() {
        return budget;
    }

    /**
     * setter for customers budget ...
     * @param budget
     */
    public void setBudget(int budget) {
        this.budget = budget;
    }

    /**
     * getter for all flights of customer ...
     * @return
     */
    public ArrayList<Flight> getFlights() {
        return Flights;
    }

    /**
     * Customer class constructor ...
     * @param username
     * @param password
     */
    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
        this.budget = 0;
    }

    /**
     * getter for customers password ...
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * setter for customers password ...
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    /**
     * adding a flight to customers list of flights ...
     * @param flight
     */
    public void addflight(Flight flight){
        Flights.add(flight);
    }

    /**
     * removig a flight from customer flight list ...
     * @param index
     */
    public void removeflight(int index){
        //int index = Flights.indexOf(flight);
        //Flights.remove(index);
        Flights.remove(index);
    }
}
