package Views;

import Model.Customer;
import Model.Flight;
import ProgramController.Programcontroller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Customer menu ...
 */
public class CustomerMenu {
    private Programcontroller programcontroller;
    public CustomerMenu(Programcontroller programcontroller){
        this.programcontroller = programcontroller;
    }

    /**
     * run method for customer menu
     * checking commands and doing correct tasks for each commands ...
     * @param customer
     * @param scanner
     */
    public void run(Customer customer,Scanner scanner){
        String input;
        //Scanner scanner = new Scanner(System.in);
        while (true){
            input = scanner.nextLine();
            /*if (input.equals("admin menu")){
                System.out.println("you don't have access to this menu");
            }
            else if (input.equals("customer menu")){

            }*/
            //if (getCommandMatcher(input,"^\\s*purchase\\s+ticket\\s+(?<origin>\\S+)\\s+(?<destination>\\S+)\\s*").find()){
            if (input.matches("\\s*purchase\\s+ticket\\s+(?<origin>\\S+)\\s+(?<destination>\\S+)\\s*")){
                Matcher matcher = getCommandMatcher(input,"\\s*purchase\\s+ticket\\s+(?<origin>\\S+)\\s+(?<destination>\\S+)\\s*");
                matcher.find();
                String origin = matcher.group("origin");
                String destination = matcher.group("destination");
                programcontroller.sortflights();
                ArrayList<Flight> Flights = programcontroller.getFlights();
                ArrayList<Flight> MyFlights = new ArrayList<>();
                ArrayList<Flight> MyFlightsorigin = new ArrayList<>();
                ArrayList<Flight> MyFlightsdestination = new ArrayList<>();
                for (int i = 0;i<Flights.size()-1;i++){
                    for (int j = i+1;j<Flights.size();j++){
                        if (Flights.get(i).getOrigin().equals(origin) && Flights.get(j).getDestination().equals(destination) && Flights.get(i).getDestination().equals(Flights.get(j).getOrigin()) && Flights.get(i).getDate().compareTo(Flights.get(j).getDate()) < 0){
                            MyFlightsorigin.add(Flights.get(i));
                            MyFlightsdestination.add(Flights.get(j));
                        }
                        else if (Flights.get(j).getOrigin().equals(origin) && Flights.get(i).getDestination().equals(destination) && Flights.get(j).getDestination().equals(Flights.get(i).getOrigin()) && Flights.get(i).getDate().compareTo(Flights.get(j).getDate()) > 0){
                            MyFlightsorigin.add(Flights.get(j));
                            MyFlightsdestination.add(Flights.get(i));
                        }
                    }
                }
                sorting(MyFlightsorigin,MyFlightsdestination);
                for (Flight flight : Flights) {
                    if (flight.getOrigin().equals(origin) && flight.getDestination().equals(destination)){
                        MyFlights.add(flight);
                    }
                }


                while (true){
                    if (!MyFlights.isEmpty()){
                        if (input.matches("\\s*\\d+\\s*") || input.matches("\\s*-\\d+\\s*")){
                            input = input.replaceAll("\\s*","");
                        }
                        if (input.matches("\\s*end\\s*")){
                            break;
                        }
                        else if (getCommandMatcher(input,"\\s*purchase\\s+ticket\\s+(?<origin>\\S+)\\s+(?<destination>\\S+)\\s*").find()){
                            for (int i = 0;i<MyFlights.size();i++){
                                System.out.println(i+1 + "- " + MyFlights.get(i).getDate() + " " + MyFlights.get(i).getAirplane_name() + " " + MyFlights.get(i).getTicket_price());
                            }
                        }
                        else if (!input.matches("\\s*\\d+\\s*") && !input.matches("\\s*-\\d+\\s*")){
                            System.out.println("invalid command!");
                        }
                        else if (Integer.parseInt(input) <= 0 || Integer.parseInt(input) > MyFlights.size()){
                            System.out.println("invalid number");
                        }
                        /*else if (programcontroller.getAirplaneByName(MyFlights.get(Integer.parseInt(input) - 1).getAirplane_name()).getCapacity() == 0){
                            System.out.println("no empty seat");
                        }*/
                        else if (MyFlights.get(Integer.parseInt(input) - 1).getCapacity() == 0){
                            System.out.println("no empty seat");
                        }
                        else if (MyFlights.get(Integer.parseInt(input) - 1).getTicket_price() > customer.getBudget()){
                            System.out.println("you don't have enough money");
                        }
                        else {
                            customer.setBudget(customer.getBudget() - MyFlights.get(Integer.parseInt(input) - 1).getTicket_price());
                            customer.addflight(MyFlights.get(Integer.parseInt(input) - 1));
                            //programcontroller.getAirplaneByName(MyFlights.get(Integer.parseInt(input) - 1).getAirplane_name()).setCapacity(programcontroller.getAirplaneByName(MyFlights.get(Integer.parseInt(input) - 1).getAirplane_name()).getCapacity() - 1);
                            programcontroller.getflight(origin,destination,MyFlights.get(Integer.parseInt(input) - 1).getDate(),MyFlights.get(Integer.parseInt(input) - 1).getAirplane_name()).setCapacity(programcontroller.getflight(origin,destination,MyFlights.get(Integer.parseInt(input) - 1).getDate(),MyFlights.get(Integer.parseInt(input) - 1).getAirplane_name()).getCapacity() - 1);
                            programcontroller.setCorporation(programcontroller.getCorporation() + MyFlights.get(Integer.parseInt(input) - 1).getTicket_price());
                            //programcontroller.getAirplaneByName(MyFlights.get(Integer.parseInt(input) - 1).getAirplane_name()).addflight(MyFlights.get(Integer.parseInt(input) - 1));
                            System.out.println("purchase successful");
                        }
                    }
                    else {
                        if (input.matches("\\s*\\d+\\s*") || input.matches("\\s*-\\d+\\s*")){
                            input = input.replaceAll("\\s*","");
                        }
                        if (input.matches("\\s*end\\s*")){
                            break;
                        }
                        else if (getCommandMatcher(input,"\\s*purchase\\s+ticket\\s+(?<origin>\\S+)\\s+(?<destination>\\S+)\\s*").find()){
                            System.out.println("There is no direct flight from " + origin + " to " + destination);
                            if (MyFlightsorigin.isEmpty()){
                                break;
                            }
                            for (int i = 0;i<MyFlightsorigin.size();i++){
                                System.out.println(i+1 + "- " + origin + "->" + MyFlightsorigin.get(i).getDestination() + " " + MyFlightsorigin.get(i).getDate() + " " + MyFlightsorigin.get(i).getTicket_price() + " | " + MyFlightsdestination.get(i).getOrigin() + "->" + destination + " " + MyFlightsdestination.get(i).getDate() + " " + MyFlightsdestination.get(i).getTicket_price());
                            }
                        }
                        else if (!input.matches("\\s*\\d+\\s*") && !input.matches("\\s*-\\d+\\s*")){
                            System.out.println("invalid command!");
                        }
                        else if (Integer.parseInt(input) <= 0 || Integer.parseInt(input) > MyFlightsorigin.size()){
                            System.out.println("invalid number");
                        }
                        /*else if (programcontroller.getAirplaneByName(MyFlightsorigin.get(Integer.parseInt(input) - 1).getAirplane_name()).getCapacity() == 0 || programcontroller.getAirplaneByName(MyFlightsdestination.get(Integer.parseInt(input) - 1).getAirplane_name()).getCapacity() == 0){
                            System.out.println("no empty seat");
                        }*/
                        else if (programcontroller.getflight(MyFlightsorigin.get(Integer.parseInt(input) - 1).getOrigin(),MyFlightsorigin.get(Integer.parseInt(input) - 1).getDestination(),MyFlightsorigin.get(Integer.parseInt(input) - 1).getDate(),MyFlightsorigin.get(Integer.parseInt(input) - 1).getAirplane_name()).getCapacity() == 0 || programcontroller.getflight(MyFlightsdestination.get(Integer.parseInt(input) - 1).getOrigin(),MyFlightsdestination.get(Integer.parseInt(input) - 1).getDestination(),MyFlightsdestination.get(Integer.parseInt(input) - 1).getDate(),MyFlightsdestination.get(Integer.parseInt(input) - 1).getAirplane_name()).getCapacity() == 0){
                            System.out.println("no empty seat");
                        }
                        else if (MyFlightsorigin.get(Integer.parseInt(input) - 1).getTicket_price() + MyFlightsdestination.get(Integer.parseInt(input) - 1).getTicket_price() > customer.getBudget()){
                            System.out.println("you don't have enough money");
                        }
                        else {
                            customer.setBudget(customer.getBudget() - MyFlightsorigin.get(Integer.parseInt(input) - 1).getTicket_price() - MyFlightsdestination.get(Integer.parseInt(input) - 1).getTicket_price());
                            customer.addflight(MyFlightsorigin.get(Integer.parseInt(input) - 1));
                            customer.addflight(MyFlightsdestination.get(Integer.parseInt(input) - 1));
                            //programcontroller.getAirplaneByName(MyFlightsorigin.get(Integer.parseInt(input) - 1).getAirplane_name()).setCapacity(programcontroller.getAirplaneByName(MyFlightsorigin.get(Integer.parseInt(input) - 1).getAirplane_name()).getCapacity() - 1);
                            //programcontroller.getAirplaneByName(MyFlightsdestination.get(Integer.parseInt(input) - 1).getAirplane_name()).setCapacity(programcontroller.getAirplaneByName(MyFlightsdestination.get(Integer.parseInt(input) - 1).getAirplane_name()).getCapacity() - 1);
                            programcontroller.getflight(MyFlightsorigin.get(Integer.parseInt(input) - 1).getOrigin(),MyFlightsorigin.get(Integer.parseInt(input) - 1).getDestination(),MyFlightsorigin.get(Integer.parseInt(input) - 1).getDate(),MyFlightsorigin.get(Integer.parseInt(input) - 1).getAirplane_name()).setCapacity(programcontroller.getflight(MyFlightsorigin.get(Integer.parseInt(input) - 1).getOrigin(),MyFlightsorigin.get(Integer.parseInt(input) - 1).getDestination(),MyFlightsorigin.get(Integer.parseInt(input) - 1).getDate(),MyFlightsorigin.get(Integer.parseInt(input) - 1).getAirplane_name()).getCapacity() - 1);
                            programcontroller.getflight(MyFlightsdestination.get(Integer.parseInt(input) - 1).getOrigin(),MyFlightsdestination.get(Integer.parseInt(input) - 1).getDestination(),MyFlightsdestination.get(Integer.parseInt(input) - 1).getDate(),MyFlightsdestination.get(Integer.parseInt(input) - 1).getAirplane_name()).setCapacity(programcontroller.getflight(MyFlightsdestination.get(Integer.parseInt(input) - 1).getOrigin(),MyFlightsdestination.get(Integer.parseInt(input) - 1).getDestination(),MyFlightsdestination.get(Integer.parseInt(input) - 1).getDate(),MyFlightsdestination.get(Integer.parseInt(input) - 1).getAirplane_name()).getCapacity() - 1);
                            programcontroller.setCorporation(programcontroller.getCorporation() + MyFlightsorigin.get(Integer.parseInt(input) - 1).getTicket_price() + MyFlightsdestination.get(Integer.parseInt(input) - 1).getTicket_price());
                            //programcontroller.getAirplaneByName(MyFlightsorigin.get(Integer.parseInt(input) - 1).getAirplane_name()).addflight(MyFlightsorigin.get(Integer.parseInt(input) - 1));
                            //programcontroller.getAirplaneByName(MyFlightsdestination.get(Integer.parseInt(input) - 1).getAirplane_name()).addflight(MyFlightsdestination.get(Integer.parseInt(input) - 1));
                            System.out.println("purchase successful");
                        }
                    }
                    input = scanner.nextLine();
                }
            }
            //else if (getCommandMatcher(input,"^\\s*charge\\s+account\\s+(?<amount>(\\d+|-\\d+))\\s*").find()){
            else if (input.matches("\\s*charge\\s+account\\s+(?<amount>(\\d+|-\\d+))\\s*")){
                Matcher matcher = getCommandMatcher(input,"\\s*charge\\s+account\\s+(?<amount>(\\d+|-\\d+))\\s*");
                matcher.find();
                int amount = Integer.parseInt(matcher.group("amount"));
                if (amount <= 0){
                    System.out.println("invalid amount");
                }
                else {
                    customer.setBudget(customer.getBudget() + amount);
                    System.out.println("account charged successfully");
                }
            }
            //else if (getCommandMatcher(input,"^\\s*cancel\\s+ticket\\s*").find()){
            else if (input.matches("\\s*cancel\\s+ticket\\s*")){
                if (customer.getFlights().isEmpty()){
                    System.out.println("you don't have any tickets");
                }
                else {
                    for (int i = 0;i<customer.getFlights().size();i++) {
                        System.out.println(i + 1 + "- " + customer.getFlights().get(i).getOrigin() + "->" + customer.getFlights().get(i).getDestination() + " " + customer.getFlights().get(i).getDate() + " " + customer.getFlights().get(i).getTicket_price());
                    }
                        while (true){
                            input = scanner.nextLine();
                            if (input.matches("\\s*\\d+\\s*") || input.matches("\\s*-\\d+\\s*")){
                                input = input.replaceAll("\\s*","");
                            }
                            if (input.matches("\\s*end\\s*")){
                                break;
                            }
                            else if (!input.matches("\\s*\\d+\\s*") && !input.matches("\\s*-\\d+\\s*")){
                                System.out.println("invalid command!");
                            }
                            else if (Integer.parseInt(input) <= 0 || Integer.parseInt(input) > customer.getFlights().size()){
                                System.out.println("invalid number");
                            }
                            else {
                                int price = customer.getFlights().get(Integer.parseInt(input) - 1).getTicket_price() * 20 / 100;
                                int refund = customer.getFlights().get(Integer.parseInt(input) - 1).getTicket_price() - price;
                                programcontroller.setCorporation(programcontroller.getCorporation() - refund);
                                customer.setBudget(customer.getBudget() + refund);
                                Flight flight = customer.getFlights().get(Integer.parseInt(input) - 1);
                                programcontroller.getflight(flight.getOrigin(), flight.getDestination(), flight.getDate(), flight.getAirplane_name()).setCapacity(programcontroller.getflight(flight.getOrigin(), flight.getDestination(), flight.getDate(), flight.getAirplane_name()).getCapacity() + 1);
                                //customer.removeflight(customer.getFlights().get(Integer.parseInt(input) - 1));
                                customer.removeflight(Integer.parseInt(input) - 1);
                                System.out.println("cancel successful");
                                break;
                            }
                        }
                }
            }
            //else if (getCommandMatcher(input,"^\\*show\\s+capital\\s*").find()){
            else if (input.matches("\\s*show\\s+capital\\s*")){
                System.out.println(customer.getBudget());
            }
            //else if (input.equals("back")){
            else if (input.matches("\\s*back\\s*")){
                break;
            }
            /*else if (input.equals("logout")){
                System.out.println("logout successful");
                break;
            }*/
            else {
                System.out.println("invalid command!");
            }
        }
    }
    private Matcher getCommandMatcher(String input, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }
    /*private ArrayList thirdparty(ArrayList<Flight> Flights,String origin,String destination){
        ArrayList<Flight> MyFlight = new ArrayList<>();
        for (int i = 0;i<Flights.size()-1;i++){
            for (int j = i + 1;j<Flights.size();j++){
                if (Flights.get(i).getOrigin().equals(origin) && Flights.get(j).getDestination().equals(destination) && Flights.get(i).getDestination().equals(Flights.get(j).getOrigin())){
                    MyFlight.add(F)
                }
            }
        }
    }*/
    private void sorting(ArrayList<Flight> MyFlightsorigin,ArrayList<Flight> MyFlightsdestination){
        for (int i = 0;i< MyFlightsorigin.size() - 1;i++){
            for (int j = i+1;j<MyFlightsorigin.size();j++){
                if (MyFlightsorigin.get(i).getDate().compareTo(MyFlightsorigin.get(j).getDate()) > 0){
                    Collections.swap(MyFlightsorigin,i,j);
                    Collections.swap(MyFlightsdestination,i,j);
                }
                else if (MyFlightsorigin.get(i).getDate().compareTo(MyFlightsorigin.get(j).getDate()) == 0){
                    if (MyFlightsorigin.get(i).getTicket_price() > MyFlightsorigin.get(j).getTicket_price()){
                        Collections.swap(MyFlightsorigin,i,j);
                        Collections.swap(MyFlightsdestination,i,j);
                    }
                    else if (MyFlightsorigin.get(i).getTicket_price() == MyFlightsorigin.get(j).getTicket_price()){
                        if (MyFlightsorigin.get(i).getAirplane_name().compareTo(MyFlightsorigin.get(j).getAirplane_name()) > 0){
                            Collections.swap(MyFlightsorigin,i,j);
                            Collections.swap(MyFlightsdestination,i,j);
                        }
                    }
                }
            }
        }
    }
}
