package com.company;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        Main program = new Main();
        //program.createDoctor();
        //program.createNurse();
        //program.createPatient();
        //program.updateUser("Patients","SThalheimer0324", "Phone", "602-234-5396");
        program.printTableColumn("Patients", "Phone");

    }

    public void command(String command){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite3\\OfficeSystem.db");
            PreparedStatement stmnt = connection.prepareStatement(command);
            stmnt.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet query(String command){
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite3\\OfficeSystem.db");
            Statement sql = connection.createStatement();
            ResultSet resultSet = sql.executeQuery(command);

            return resultSet;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void createPatient(){
        Main program = new Main();
        String first;
        String last;
        LocalDate date;
        String pass;
        String address;
        String insurance;
        String email;
        String pharmacy;
        String phone;
        String doctorId;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate;
        String command;

        System.out.println("Enter first name");
        first = in.next();

        System.out.println("Enter last name");
        last = in.next();

        System.out.println("Enter birth month [1-12]");
        int month = in.nextInt();
        in.nextLine();

        System.out.println("Enter birth day [1-31]");
        int day = in.nextInt();
        in.nextLine();

        System.out.println("Enter birth year [1940-2021]");
        int year = in.nextInt();
        in.nextLine();

        System.out.println("Please enter your password");
        pass = in.next();
        in.nextLine();

        System.out.println("Please enter your address");
        address = in.nextLine();

        System.out.println("Please enter your insurance");
        insurance = in.nextLine();

        System.out.println("Please enter your email");
        email = in.nextLine();

        System.out.println("Please enter your pharmacy");
        pharmacy = in.nextLine();

        System.out.println("Please enter your phone");
        phone = in.nextLine();


        ResultSet output = program.query("Select First, Last, ID from Doctors;");
        ArrayList<String> docNames = new ArrayList<String>();
        try{
            while(output.next()){
                String doc = output.getString("First") + " " + output.getString("Last") + " :" + output.getString("ID");
                docNames.add(doc);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        for(String doc : docNames){
            System.out.println(doc);
        }

        System.out.println("Please enter your doctor's id");
        doctorId = in.nextLine();

        date = LocalDate.of(year,month,day);
        formattedDate = date.format(formatter);
        command = "INSERT INTO Patients (First, Last, DOB, Password, ID, Address, Insurance, Email, Pharmacy, Phone, Doctor)" +
                " VALUES" +
                " ('" + first +
                "','" + last + "','"+formattedDate + "','"+pass+"','"+program.createID(first,last,formattedDate) +
                "','" + address + "','" + insurance + "','" + email + "','" + pharmacy + "','" + phone +
                "','"+ doctorId+"');";
        program.command(command);
    }

    public void createDoctor(){
        Main program = new Main();
        String first;
        String last;
        LocalDate date;
        String pass;
        String areaOfExpertise;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate;
        String command;

        System.out.println("Enter first name");
        first = in.next();

        System.out.println("Enter last name");
        last = in.next();

        System.out.println("Enter birth month [1-12]");
        int month = in.nextInt();
        in.nextLine();

        System.out.println("Enter birth day [1-31]");
        int day = in.nextInt();
        in.nextLine();

        System.out.println("Enter birth year [1940-2021]");
        int year = in.nextInt();
        in.nextLine();

        System.out.println("Please enter your password");
        pass = in.next();
        in.nextLine();

        System.out.println("Please enter your area of expertise");
        areaOfExpertise = in.nextLine();

        date = LocalDate.of(year,month,day);
        formattedDate = date.format(formatter);
        command = "INSERT INTO Doctors (First, Last, DOB, Password, ID, Expertise) VALUES ('" + first +
                "','"+ last + "','"+formattedDate + "','"+pass+"','"+program.createID(first,last,formattedDate) +
                "','"+ areaOfExpertise+"');";
        program.command(command);
    }

    public void createNurse(){
        Main program = new Main();
        String first;
        String last;
        LocalDate date;
        String pass;
        String doctorId;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate;
        String command;

        System.out.println("Enter first name");
        first = in.next();

        System.out.println("Enter last name");
        last = in.next();

        System.out.println("Enter birth month [1-12]");
        int month = in.nextInt();
        in.nextLine();

        System.out.println("Enter birth day [1-31]");
        int day = in.nextInt();
        in.nextLine();

        System.out.println("Enter birth year [1940-2021]");
        int year = in.nextInt();
        in.nextLine();

        System.out.println("Please enter your password");
        pass = in.next();
        in.nextLine();

        ResultSet output = program.query("Select First, Last, ID from Doctors;");
        ArrayList<String> docNames = new ArrayList<String>();
        try{
            while(output.next()){
                String doc = output.getString("First") + " " + output.getString("Last") + " :" + output.getString("ID");
                docNames.add(doc);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        for(String doc : docNames){
            System.out.println(doc);
        }

        System.out.println("Please enter doctor id");
        doctorId = in.nextLine();

        date = LocalDate.of(year,month,day);
        formattedDate = date.format(formatter);
        command = "INSERT INTO Nurses (First, Last, DOB, Password, ID, Doctor) VALUES ('" + first +
                "','"+ last + "','"+formattedDate + "','"+pass+"','"+program.createID(first,last,formattedDate) +
                "','"+ doctorId+"');";
        program.command(command);
    }

    public String createID(String fName, String lName, String formattedDate){
        String id =  fName.charAt(0)+lName+formattedDate.substring(0,2)+formattedDate.substring(3,5);
        return id;
    }

    public void updateUser(String table , String ID, String column, String newInfo){
        Main run = new Main();
        String command = "UPDATE " + table;
        command += " SET " + column + " = " + "'" + newInfo + "'";
        command += " WHERE ID = " + "'" + ID + "';";
        run.command(command);
    }

    public void printTableColumn(String table, String column){
        Main run = new Main();
        ResultSet log;

        log = run.query("Select * FROM " + table + ";");
        try{
            while(log.next()){
                System.out.println(log.getString(column));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
