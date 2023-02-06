package Views;

import Model.Airplane;
import Model.Flight;
import ProgramController.Programcontroller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Admin menu ....
 */
public class AdminMenu {
    private Programcontroller programcontroller;
    public AdminMenu(Programcontroller programcontroller){
        this.programcontroller = programcontroller;
    }

    /**
     * Our run method to checking commands and doing correct tasks ...
     * @param scanner
     */
    public void run(Scanner scanner){
        String input;
        //Scanner scanner = Scanner(System.in);
        while (true){
            input = scanner.nextLine();
            /*if (input.equals("customer menu")){
                System.out.println("you don't have access to this menu");
            }
            else if (input.equals("admin menu")){

            }*/
            /*else if (input.equals("logout")){
                System.out.println("logout successful");
                break;
            }*/
            //if (getCommandMatcher(input,"^add\\s+airplane\\s+(?<name>\\S+)\\s+(?<capacity>(-\\d+|\\d+))\\s*").find()){
            if (input.matches("\\s*add\\s+airplane\\s+(?<name>\\S+)\\s+(?<capacity>(-\\d+|\\d+))\\s*")){
                Matcher matcher = getCommandMatcher(input,"\\s*add\\s+airplane\\s+(?<name>\\S+)\\s+(?<capacity>(-\\d+|\\d+))\\s*");
                matcher.find();
                String name = matcher.group("name");
                int capacity = Integer.parseInt(matcher.group("capacity"));

                Airplane airplane = programcontroller.getAirplaneByName(name);
                if (airplane != null){
                    System.out.println("an airplane exists with this name");
                }
                else if (capacity < 10){
                    System.out.println("invalid capacity");
                }
                else {
                    Airplane newairplane = new Airplane(name,capacity);
                    programcontroller.addAirplane(newairplane);
                    System.out.println("plane created successfully");
                }
            }
            //else if (getCommandMatcher(input,"^add\\s+flight\\s+(?<origin>\\S+)\\s+(?<destination>\\S+)\\s+(?<date>\\d{4}-\\d{2}-\\d{2})\\s+(?<name>\\S+)\\s+(?<ticketprice>(-\\d+|\\d+))\\s*").find()){
            else if (input.matches("\\s*add\\s+flight\\s+(?<origin>\\S+)\\s+(?<destination>\\S+)\\s+(?<date>\\d{4}-\\d{2}-\\d{2})\\s+(?<name>\\S+)\\s+(?<ticketprice>(-\\d+|\\d+))\\s*")){
                Matcher matcher = getCommandMatcher(input,"\\s*add\\s+flight\\s+(?<origin>\\S+)\\s+(?<destination>\\S+)\\s+(?<date>\\d{4}-\\d{2}-\\d{2})\\s+(?<name>\\S+)\\s+(?<ticketprice>(-\\d+|\\d+))\\s*");
                matcher.find();
                String name = matcher.group("name");
                String date = matcher.group("date");
                String origin = matcher.group("origin");
                String destination = matcher.group("destination");
                int ticket_price = Integer.parseInt(matcher.group("ticketprice"));
                Airplane airplane = programcontroller.getAirplaneByName(name);
                if (airplane == null){
                    System.out.println("no airplane exists with this name");
                }
                else if (ticket_price <= 0){
                    System.out.println("invalid ticket price");
                }
                else if (programcontroller.checkdate(airplane,date)){
                    System.out.println("This aircraft already has a flight on this date");
                }
                else {
                    int capacity = programcontroller.getAirplaneByName(name).getCapacity();
                    Flight flight = new Flight(date,origin,destination,name,ticket_price,capacity);
                    airplane.addflight(flight);
                    programcontroller.addflight(flight);
                    System.out.println("flight created successfully");
                }
            }
            //else if (getCommandMatcher(input,"^show\\s+all\\s+flights\\s*").find()){
            else if (input.matches("\\s*show\\s+all\\s+flights\\s*")){
                programcontroller.sortflights();
                if (programcontroller.getFlights().isEmpty()){
                    System.out.println("nothing");
                }
                else {
                    for (int i = 0;i<programcontroller.getFlights().size();i++){
                        System.out.println(i+1 + "- " + programcontroller.getFlights().get(i).getOrigin() + "->" + programcontroller.getFlights().get(i).getDestination() + " " + programcontroller.getFlights().get(i).getDate() + " " + programcontroller.getFlights().get(i).getAirplane_name() + " " + programcontroller.getFlights().get(i).getTicket_price());
                    }
                }
            }
            //else if (getCommandMatcher(input,"show\\s+flights\\s+on\\s+(?<date>\\d{4}-\\d{2}-\\d{2})\\s*").find()){
            else if (input.matches("\\s*show\\s+flights\\s+on\\s+(?<date>\\d{4}-\\d{2}-\\d{2})\\s*")){
                Matcher matcher = getCommandMatcher(input,"\\s*show\\s+flights\\s+on\\s+(?<date>\\d{4}-\\d{2}-\\d{2})\\s*");
                matcher.find();
                int flag = 0;
                int iterator = 1;
                String date = matcher.group("date");
                programcontroller.sortflights();
                for (int i = 0;i<programcontroller.getFlights().size();i++){
                    if (programcontroller.getFlights().get(i).getDate().equals(date)){
                        System.out.println(iterator + "- " + programcontroller.getFlights().get(i).getOrigin() + "->" + programcontroller.getFlights().get(i).getDestination() + " " + programcontroller.getFlights().get(i).getDate() + " " + programcontroller.getFlights().get(i).getAirplane_name() + " " + programcontroller.getFlights().get(i).getTicket_price());
                        flag = 1;
                        iterator++;
                    }
                }
                if (flag == 0){
                    System.out.println("nothing");
                }
            }
            //else if (getCommandMatcher(input,"show\\s+airplanes\\s*").find()){
            else if (input.matches("\\s*show\\s+airplanes\\s*")){
                programcontroller.sortairplanesbyascii();
                if (programcontroller.getAirplanes().isEmpty()){
                    System.out.println("nothing");
                }
                else {
                    for (int i = 0;i<programcontroller.getAirplanes().size();i++){
                        System.out.println(i+1 + "- " + programcontroller.getAirplanes().get(i).getName() + " : " + programcontroller.getAirplanes().get(i).getFlights().size());
                    }
                }
            }
            //else if (getCommandMatcher(input,"^\\s*show\\s+capital\\s*").find()){
            else if (input.matches("\\s*show\\s+capital\\s*")){
                System.out.println(programcontroller.getCorporation());
            }
            else if (input.matches("\\s*back\\s*")){
                break;
            }
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
}
