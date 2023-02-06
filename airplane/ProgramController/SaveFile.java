package ProgramController;

import Model.Admin;
import Model.Airplane;
import Model.Customer;
import Model.Flight;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Main class for saving and loading from file ...
 */
public class SaveFile implements Serializable {
    @Serial
    private static final long serialVersionUID = 13799434508676095L;
    private static final String autoPath = "save";

    /**
     * saving to file ...
     * @param programcontroller
     */
    public static void save(Programcontroller programcontroller){
        SaveFile savings = new SaveFile(programcontroller);
        try {
            FileOutputStream fileStream;
            fileStream = new FileOutputStream(autoPath);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            objectStream.writeObject(savings);
            objectStream.close();
            fileStream.close();
        } catch (Exception e) {
            System.out.println("An Error occurred during saving ");
            e.printStackTrace();
        }
    }
    private final Admin admin;
    private final ArrayList<Customer> customers;
    private final ArrayList<Flight> flights;
    private final ArrayList<Airplane> airplanes;
    private Programcontroller programcontroller;


    public SaveFile(Programcontroller programcontroller) {
        this.programcontroller = programcontroller;
        this.admin = programcontroller.getAdmin();
        this.customers = programcontroller.getCustomers();
        this.flights = programcontroller.getFlights();
        this.airplanes = programcontroller.getAirplanes();

    }

    /**
     * loading from file ...
     * @param programcontroller
     */
    public static void load(Programcontroller programcontroller){

        try {
            FileInputStream fileStream;
            fileStream = new FileInputStream(autoPath);
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);
            SaveFile saving  = (SaveFile) objectStream.readObject();
            programcontroller.setAdmin(saving.admin);
            programcontroller.setAirplanes(saving.airplanes);
            programcontroller.setCustomers(saving.customers);
            programcontroller.setFlights(saving.flights);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
