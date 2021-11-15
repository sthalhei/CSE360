package com.company;
import java.sql.*;
import java.time.Period;
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
        //program.updateUser("Visits","JJanerf0617", "Notes", "Cream Cheese");
        //program.printTableColumn("Patients", "Phone");
        //program.createVisit();
        //program.doctorVisit("1","Xanax","New Summary Ladie","New notes ladie");
        //program.createMessage("Testing Testing 123", "PKonstantinov0412", "JMichaels0425");
        System.out.println(program.retrieveSingleColumn("Visits", "HealthConcerns", "VisitID", "1"));
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

    public void doctorVisit(String visitID, String prescriptions, String summary, String notes){
        Main run = new Main();
        String command = "UPDATE Visits SET (Prescriptions, Notes, Summary) = ('" + prescriptions +
                "','"+ notes + "','" + summary+"')" + "WHERE VisitID" + " = '"+ visitID + "';";
        run.command(command);
    };

    public void createPatient(){
        Main program = new Main();
        String first;
        String last;
        LocalDate date;
        LocalDate currentDate;
        String pass;
        String address;
        String insurance;
        String email;
        String pharmacy;
        String phone;
        String doctorId;
        int age;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate;
        String command;
        String userCommand;

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
        currentDate = LocalDate.now();

        age = Period.between(date,currentDate).getYears();

        formattedDate = date.format(formatter);
        command = "INSERT INTO Patients (First, Last, DOB, Password, ID, Address, Insurance, Email, Pharmacy, Phone, Doctor, Age)" +
                " VALUES" +
                " ('" + first +
                "','" + last + "','"+formattedDate + "','"+pass+"','"+program.createID(first,last,formattedDate) +
                "','" + address + "','" + insurance + "','" + email + "','" + pharmacy + "','" + phone +
                "','"+ doctorId+ "','"+ age +"');";

        userCommand = "INSERT INTO Users (FirstName, LastName, DOB, Password, ID) VALUES ('" + first +
                    "','" + last +
                    "','" + formattedDate +
                    "','" + pass +
                    "','" + program.createID(first,last,formattedDate) + "');";
        program.command(command);
        program.command(userCommand);

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
        String userCommand;

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
        userCommand = "INSERT INTO Users (FirstName, LastName, DOB, Password, ID) VALUES ('" + first +
                "','" + last +
                "','" + formattedDate +
                "','" + pass +
                "','" + program.createID(first,last,formattedDate) + "');";
        program.command(command);
        program.command(userCommand);
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
        String userCommand;

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
        userCommand = "INSERT INTO Users (FirstName, LastName, DOB, Password, ID) VALUES ('" + first +
                "','" + last +
                "','" + formattedDate +
                "','" + pass +
                "','" + program.createID(first,last,formattedDate) + "');";
        program.command(command);
        program.command(userCommand);
    }

    public void createVisit(){
        Main program = new Main();
        String ID;
        String allergies;
        String healthConcerns;
        LocalDate date;
        String prescriptions;
        String notes;
        String summary;
        String weight;
        String height;
        String temperature;
        String pressure;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate;
        String command;
        String allergyCommand;
        String healthConcernCommand;
        String prescriptionCommand;

        System.out.println("Enter patient ID");
        ID = in.nextLine();

        System.out.println("Enter allergies");
        allergies = in.nextLine();

        System.out.println("Please enter health concerns");
        healthConcerns = in.nextLine();

        System.out.println("Please enter prescriptions");
        prescriptions = in.nextLine();

        System.out.println("Please enter notes");
        notes = in.nextLine();

        System.out.println("Please enter summary");
        summary = in.nextLine();

        System.out.println("Please enter weight");
        weight = in.nextLine();

        System.out.println("Please enter height [feet.inches]");
        height = in.nextLine();

        System.out.println("Please enter temperature");
        temperature = in.nextLine();

        System.out.println("Please enter blood pressure");
        pressure = in.nextLine();


        date = LocalDate.now();
        formattedDate = date.format(formatter);
        command = "INSERT INTO Visits (PatientID, Allergies, HealthConcerns, Prescriptions, Notes, Summary, Date, Weight, Height, Temperature, Pressure)" +
                " VALUES" +
                " ('" + ID +
                "','" + allergies + "','"+healthConcerns + "','"+prescriptions+"','"+notes +
                "','" + summary + "','" + formattedDate + "','" + weight + "','" + height + "','" + temperature +
                "','"+ pressure+"');";
        allergyCommand = "INSERT INTO Allergies (Allergy, PatientID) VALUES ('"+ allergies +
                "','" + ID + "');";
        healthConcernCommand = "INSERT INTO HealthConcerns (HealthConcern, PatientID) VALUES ('"+ healthConcerns +
                "','" + ID + "');";
        prescriptionCommand = "INSERT INTO Prescriptions (Prescription, PatientID) VALUES ('"+ prescriptions +
                "','" + ID + "');";
        program.command(command);
        if(!allergies.equals("N/A")){
            program.command(allergyCommand);
        }
        if(!healthConcerns.equals("N/A")){
            program.command(healthConcernCommand);
        }
        if(!prescriptionCommand.equals("N/A")){
            program.command(prescriptionCommand);
        }


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

    public void createMessage(String message, String fromID, String toID){
        Main run = new Main();
        LocalDate currentDate;
        String formattedDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String command;

        currentDate = LocalDate.now();
        formattedDate = currentDate.format(formatter);
        command = "INSERT INTO Messages (Message, fromID, toID, Date) VALUES ('"
                +message+ "','" + fromID + "','" + toID +"','" + formattedDate + "');";
        run.command(command);

    }

    public String retrieveSingleColumn(String table, String column, String primKey,String primKeyValue){
        Main run = new Main();
        ResultSet result;
        String answer;
        String command;

        command = "SELECT " + column + " FROM " + table + " WHERE " +primKey+ " = '" + primKeyValue + "';";
        result = run.query(command);
        try{
            answer = result.getString(column);
            return answer;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;

    }



}
