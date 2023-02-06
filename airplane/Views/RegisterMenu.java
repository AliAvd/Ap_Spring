package Views;
/**
 * Main register menu ...
 */

import Model.Admin;
import Model.Customer;
import ProgramController.Programcontroller;
import ProgramController.SaveFile;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterMenu {
    private Programcontroller Programcontroller = new Programcontroller();
    public RegisterMenu(Programcontroller Programcontroller){
        this.Programcontroller = Programcontroller;
    }
    AdminMenu adminmenu = new AdminMenu(Programcontroller);
    CustomerMenu customermenu = new CustomerMenu(Programcontroller);

    /**
     * run method for checking commands and doing correct tasks
     * And actually can use to switching between menus ...
     */
    public void run(){
        Scanner scanner = new Scanner(System.in);
        String input;
        //SaveFile.load(this.Programcontroller);
        while (true){
            input = scanner.nextLine();
            //if (getCommandMatcher(input,"^\\s*register\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*$").find()){
            if (input.matches("\\s*register\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*")){
                Matcher matcher = getCommandMatcher(input,"\\s*register\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*");
                matcher.find();
                String username = matcher.group("username");
                String password = matcher.group("password");
                if (!username_check(username) || !minimumalphabet(username)){
                    System.out.println("username format is invalid");
                }
                else if (Programcontroller.getCustomerByName(username) != null || Programcontroller.getAdmibyName(username) != null){
                    System.out.println("a user exists with this username");
                }
                else if (!password_check(password)){
                    System.out.println("password format is invalid");
                }
                else if (!passwordStrong(password) || password.length() < 5){
                    System.out.println("password is weak");
                }
                else {
                    Customer customer = new Customer(username,password);
                    Programcontroller.addcustomer(customer);
                    System.out.println("register successful");
                }

            }
            //else if (getCommandMatcher(input,"^\\s*register\\s+as\\s+admin\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*").find()){
            else if (input.matches("\\s*register\\s+as\\s+admin\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*")){
                Matcher matcher = getCommandMatcher(input,"\\s*register\\s+as\\s+admin\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*");
                matcher.find();
                String username = matcher.group("username");
                String password = matcher.group("password");
                if (Programcontroller.getAdmin() != null){
                    System.out.println("admin user already created");
                }
                else if (!username_check(username) || !minimumalphabet(username)){
                    System.out.println("username format is invalid");
                }
                else if (Programcontroller.getCustomerByName(username) != null){
                    System.out.println("a user exists with this username");
                }
                else if (!password_check(password)){
                    System.out.println("password format is invalid");
                }
                else if (!passwordStrong(password) || password.length() < 5){
                    System.out.println("password is weak");
                }
                else {
                    Admin admin = new Admin(username,password);
                    Programcontroller.setAdmin(admin);
                    System.out.println("admin user created successfully");
                }
            }
            //else if (getCommandMatcher(input,"^\\s*login\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*").find()){
            else if (input.matches("\\s*login\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*")){
                Matcher matcher = getCommandMatcher(input,"\\s*login\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*");
                matcher.find();
                String username = matcher.group("username");
                String password = matcher.group("password");
                if (Programcontroller.getCustomerByName(username) == null && Programcontroller.getAdmibyName(username) == null){
                    System.out.println("no user exists with this username");
                }
                else if (Programcontroller.getCustomerByName(username) != null){
                    Customer customer = Programcontroller.getCustomerByName(username);
                    if (!customer.getPassword().equals(password)){
                        System.out.println("incorrect password");
                    }
                    else {
                        System.out.println("login successful");
                        outer :while (true){
                            inner :while (true){
                                input = scanner.nextLine();
                                if (input.matches("\\s*customer\\s+menu\\s*")){
                                    break inner;
                                }
                                else if (input.matches("\\s*admin\\s+menu\\s*")){
                                    System.out.println("you don't have access to this menu");
                                }
                                else if (input.matches("\\s*logout\\s*")){
                                    System.out.println("logout successful");
                                    break outer;
                                }
                                else {
                                    System.out.println("invalid command!");
                                }
                            }
                            customermenu.run(customer,scanner);
                        }
                    }
                }
                else {
                    Admin admin = Programcontroller.getAdmibyName(username);
                    if (!admin.getPassword().equals(password)){
                        System.out.println("incorrect password");
                    }
                    else {
                        System.out.println("login successful");
                        outer :while (true){
                            inner :while (true){
                                input = scanner.nextLine();
                                if (input.matches("\\s*admin\\s+menu\\s*")){
                                    break inner;
                                }
                                else if (input.matches("\\s*customer\\s+menu\\s*")){
                                    System.out.println("you don't have access to this menu");
                                }
                                else if (input.matches("\\s*logout\\s*")){
                                    System.out.println("logout successful");
                                    break outer;
                                }
                                else {
                                    System.out.println("invalid command!");
                                }
                            }
                            adminmenu.run(scanner);
                        }
                    }
                }
            }
            //else if (getCommandMatcher(input,"^change\\s+password\\s+(?<username>\\S+)\\s+(?<oldpassword>\\S+)\\s+(?<newpassword>\\S+)\\s*").find()){
            else if (input.matches("\\s*change\\s+password\\s+(?<username>\\S+)\\s+(?<oldpassword>\\S+)\\s+(?<newpassword>\\S+)\\s*")){
                Matcher matcher = getCommandMatcher(input,"\\s*change\\s+password\\s+(?<username>\\S+)\\s+(?<oldpassword>\\S+)\\s+(?<newpassword>\\S+)\\s*");
                matcher.find();
                String username = matcher.group("username");
                String oldPassword = matcher.group("oldpassword");
                String newPassword = matcher.group("newpassword");
                if (Programcontroller.getCustomerByName(username) == null && Programcontroller.getAdmibyName(username) == null){
                    System.out.println("no user exists with this username");
                }
                else {
                    if (Programcontroller.getCustomerByName(username)!=null){
                        Customer customer = Programcontroller.getCustomerByName(username);
                        if (!customer.getPassword().equals(oldPassword)){
                            System.out.println("incorrect password");
                        }
                        else {
                            if (!password_check(newPassword)){
                                System.out.println("new password format is invalid");
                            }
                            else {
                                if (!passwordStrong(newPassword) || newPassword.length() < 5){
                                    System.out.println("new password is weak");
                                }
                                else {
                                    Customer newcustomer = new Customer(username,newPassword);
                                    //Programcontroller.changePassword(customer,newcustomer);
                                    Programcontroller.getCustomerByName(username).setPassword(newPassword);
                                    System.out.println("password changed successfully");
                                }
                            }
                        }
                    }
                    else if (Programcontroller.getAdmibyName(username)!=null){
                        Admin admin = Programcontroller.getAdmibyName(username);
                        if (!admin.getPassword().equals(oldPassword)){
                            System.out.println("incorrect password");
                        }
                        else {
                            if (!password_check(newPassword)){
                                System.out.println("new password format is invalid");
                            }
                            else {
                                if (!passwordStrong(newPassword) || newPassword.length() < 5){
                                    System.out.println("new password is weak");
                                }
                                else {
                                    Programcontroller.getAdmibyName(username).setPassword(newPassword);
                                    System.out.println("password changed successfully");
                                }
                            }
                        }
                    }
                }
            }
            //else if (getCommandMatcher(input,"^remove\\s+account\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*").find()){
            else if (input.matches("\\s*remove\\s+account\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*")){
                Matcher matcher = getCommandMatcher(input,"\\s*remove\\s+account\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*");
                matcher.find();
                String username = matcher.group("username");
                String password = matcher.group("password");
                if (Programcontroller.getCustomerByName(username) == null && Programcontroller.getAdmibyName(username) == null){
                    System.out.println("no user exists with this username");
                }
                else {
                    if (Programcontroller.getCustomerByName(username) != null){
                        Customer customer = Programcontroller.getCustomerByName(username);
                        if (!customer.getPassword().equals(password)){
                            System.out.println("incorrect password");
                        }
                        else {
                            Programcontroller.removecustomer(customer);
                            System.out.println("account removed successfully");
                        }
                    }
                    else if (Programcontroller.getAdmibyName(username) != null){
                        Admin admin = Programcontroller.getAdmibyName(username);
                        if (!admin.getPassword().equals(password)){
                            System.out.println("incorrect password");
                        }
                        else {
                            Programcontroller.removeadmin();
                            System.out.println("account removed successfully");
                        }
                    }
                }
            }
            else if (input.matches("\\s*Exit\\s*")){
                SaveFile.save(this.Programcontroller);
                break;
            }
            else {
                System.out.println("invalid command!");
            }

        }




    }
    private Matcher getCommandMatcher(String input,String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }
    private boolean username_check(String username){
        if (username.matches("[a-zA-Z\\d_]+")){
            return true;
        }
        else {
            return false;
        }

    }
    private boolean minimumalphabet(String input){
        for (int i = 0;i<input.length();i++){
            int number = input.charAt(i);
            if ((number > 64 && number < 91) || (number > 96 && number < 123)){
                return true;
            }
        }
        return false;
    }
    private boolean password_check(String password){
        if (password.matches("[a-zA-Z_\\d]+")){
            return true;
        }
        else {
            return false;
        }
    }
    private boolean passwordStrong(String password){
        int existnumber = 0;
        int existlittle = 0;
        int existcapital = 0;
        for (int i = 0;i<password.length();i++){
            int number = password.charAt(i);
            if (number > 47 && number < 58){
                existnumber = 1;
            }
            if (number > 64 && number < 91){
                existcapital = 1;
            }
            if (number > 96 && number < 123){
                existlittle = 1;
            }
        }
        if (existcapital == 1 && existnumber == 1 && existlittle == 1){
            return true;
        }
        else {
            return false;
        }
    }
}
