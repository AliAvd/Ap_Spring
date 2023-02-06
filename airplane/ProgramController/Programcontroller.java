package ProgramController;

import Model.Admin;
import Model.Airplane;
import Model.Customer;
import Model.Flight;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Our main programcontroller and body of the project ...
 */

public class Programcontroller implements Serializable {
    @Serial
    private static final long serialVersionUID = 6113799434508676095L;
    private int corporation = 0;
    private ArrayList<Customer> Customers = new ArrayList<>();
    private ArrayList<Airplane> Airplanes = new ArrayList<>();
    private ArrayList<Flight> Flights = new ArrayList<>();
    private Admin admin;

    /**
     * adding a customer to customers list ...
     * @param customer
     */
    public void addcustomer(Customer customer){
        this.Customers.add(customer);
    }

    /**
     * getting customer from their username ...
     * @param username
     * @return
     */
    public Customer getCustomerByName(String username){
        for (Customer customer : this.Customers) {
            if (customer.getUsername().equals(username)){
                return customer;
            }
        }
        return null;
    }

    /**
     * setter for customers
     * Usage for loading from file ...
     * @param customers
     */
    public void setCustomers(ArrayList<Customer> customers) {
        Customers = customers;
    }

    /**
     * setter for airplanes
     * Usage for loading from file ...
     * @param airplanes
     */
    public void setAirplanes(ArrayList<Airplane> airplanes) {
        Airplanes = airplanes;
    }

    /**
     * setter for flights
     * Usage for loading from file ...
     * @param flights
     */
    public void setFlights(ArrayList<Flight> flights) {
        Flights = flights;
    }

    /**
     * Getting all customers that have registered ...
     * @return
     */
    public ArrayList<Customer> getCustomers(){
        ArrayList<Customer> customers = new ArrayList<>();
        for (Customer customer : this.Customers) {
            customers.add(customer);
        }
        return customers;
    }

    /**
     * setter for our sole admin ...
     * @param admin
     */
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    /**
     * getter for admin ...
     * @return
     */
    public Admin getAdmin() {
        return admin;
    }

    /**
     * getting our admin by its username ...
     * @param username
     * @return
     */
    public Admin getAdmibyName(String username){
        if (this.admin!=null && this.admin.getUsername().equals(username)){
            return admin;
        }
        return null;
    }

    /**
     * removing a customer from customers list ...
     * @param customer
     */
    public void removecustomer(Customer customer){
        int index = Customers.indexOf(customer);
        Customers.remove(index);
    }

    /**
     * removing admin ...
     */
    public void removeadmin(){
        this.setAdmin(null);
    }

    /**
     * getting a airplane from its username ...
     * @param name
     * @return
     */
    public Airplane getAirplaneByName(String name){
        for (Airplane airplane : Airplanes) {
            if (airplane.getName().equals(name)){
                return airplane;
            }
        }
        return null;
    }

    /**
     * getting a flight from some specific inputs ...
     * @param origin
     * @param destination
     * @param date
     * @param airplane_name
     * @return
     */
    public Flight getflight(String origin,String destination,String date,String airplane_name){
        for (Flight flight : Flights) {
            if (flight.getDate().equals(date) && flight.getOrigin().equals(origin) && flight.getDestination().equals(destination) && flight.getAirplane_name().equals(airplane_name)){
                return flight;
            }
        }
        return null;
    }

    /**
     * adding airplane to our airplanes list ...
     * @param airplane
     */
    public void addAirplane(Airplane airplane){
        Airplanes.add(airplane);
    }

    /**
     * checking date of a airplane ...
     * @param airplane
     * @param date
     * @return
     */
    public boolean checkdate(Airplane airplane,String date){
        for (Flight flight : airplane.getFlights()) {
            if (flight.getDate().equals(date)){
                return true;
            }
        }
        return false;
    }

    /**
     * adding a flight to our flights list ...
     * @param flight
     */
    public void addflight(Flight flight){
        Flights.add(flight);
    }

    /**
     * getting all flights that have been created ...
     * @return
     */

    public ArrayList<Flight> getFlights() {
        return Flights;
    }

    /**
     * getting corporation ...
     * @return
     */
    public int getCorporation() {
        return corporation;
    }

    /**
     * getting all airplanes ...
     * @return
     */
    public ArrayList<Airplane> getAirplanes() {
        return Airplanes;
    }

    /**
     * setter for corporation ...
     * @param corporation
     */
    public void setCorporation(int corporation) {
        this.corporation = corporation;
    }

    /**
     * sorting all flights
     * sorting order is :
     * first comparing their date
     * Then comparing ticket price
     * and at last comparing their airplane name ...
     */
    public void sortflights(){
        for (int i = 0;i<Flights.size() - 1;i++){
            for (int j = i + 1;j<Flights.size();j++){
                if (Flights.get(i).getDate().compareTo(Flights.get(j).getDate()) > 0){
                    Collections.swap(Flights,i,j);
                }
                else if (Flights.get(i).getDate().compareTo(Flights.get(j).getDate()) == 0){
                    if (Flights.get(i).getTicket_price() > Flights.get(j).getTicket_price()){
                        Collections.swap(Flights,i,j);
                    }
                    else if (Flights.get(i).getTicket_price() == Flights.get(j).getTicket_price()){
                        if (Flights.get(i).getAirplane_name().compareTo(Flights.get(j).getAirplane_name()) > 0){
                            Collections.swap(Flights,i,j);
                        }
                    }
                }
            }
        }
    }

    /**
     * sorting airplanes from their ascii
     */
    public void sortairplanesbyascii(){
        for (int i = 0;i<Airplanes.size() - 1;i++){
            for (int j = i + 1;j<Airplanes.size();j++){
                if (Airplanes.get(i).getName().compareTo(Airplanes.get(j).getName()) > 0){
                    Collections.swap(Airplanes,i,j);
                }
            }
        }
    }

}
