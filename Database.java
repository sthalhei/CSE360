//package com.company;
import java.sql.*;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.time.LocalDate;

public class Database {
    Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        Database program = new Database();
        //program.createDoctor();
        //program.createNurse();
        //program.createPatient();
        //program.updateUser("Visits","JJanerf0617", "Notes", "Cream Cheese");
        //program.printTableColumn("Users", "password");
        //program.createVisit();
        //program.doctorVisit("1","Xanax","New Summary Ladie","New notes ladie");
        //program.createMessage("Testing Testing 123", "PKonstantinov0412", "JMichaels0425");
        //System.out.println(program.retrieveSingleColumn("Visits", "HealthConcerns", "VisitID", "1"));
        //System.out.println(program.authenticate("PKonstantinov0113", "12345"));
    }

    public Connection connect() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite3\\java\\OfficeSystem.db");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
    public void command(String command){
    	Database run = new Database();
    	Connection connection = run.connect();
    	PreparedStatement stmnt = null;
        try {
            stmnt = connection.prepareStatement(command);
            stmnt.executeUpdate();


        }catch (Exception e){
            e.printStackTrace();
        }
      
    }

    public ResultSet query(String command){
    	Database run = new Database();
    	Connection connection = run.connect();
    	ResultSet rs = null;
        try {
            
            Statement sql = connection.createStatement();
            rs = sql.executeQuery(command);

        }catch (Exception e){
            e.printStackTrace();
        }
      
        return rs;
    }

    public void doctorVisit(String visitID, String prescriptions, String summary, String notes){
        Database run = new Database();
        String command = "UPDATE Visits SET (Prescriptions, Notes, Summary) = ('" + prescriptions +
                "','"+ notes + "','" + summary+"')" + "WHERE VisitID" + " = '"+ visitID + "';";
        run.command(command);
    };
    
    //Return an ArrayList of patients and their IDs.
  	public ArrayList getPatients() {
      	Database program = new Database();
    	  	ResultSet output = program.query("Select First, Last, ID from Patients;");
          ArrayList<String> patientsNames = new ArrayList<String>();
          try{
              while(output.next()){
                  String patient = output.getString("First") + " " + output.getString("Last")+ " " + output.getString("ID");
                  patientsNames.add(patient);
              }
              output.close();
          }
          catch (SQLException e){
              e.printStackTrace();
          }
  		return patientsNames;
    }
  	
  //Return an ArrayList of patients and their IDs.
  	public ArrayList getPatientsForaDoc(String doctorID) {
      	Database program = new Database();
    	  	ResultSet output = program.query("Select First, Last, ID from Patients WHERE Doctor ='" + doctorID + "';");
          ArrayList<String> patientsNames = new ArrayList<String>();
          try{
              while(output.next()){
                  String patient = output.getString("First") + " " + output.getString("Last")+ " " + output.getString("ID");
                  patientsNames.add(patient);
              }
              output.close();
          }
          catch (SQLException e){
              e.printStackTrace();
          }
  		return patientsNames;
    }
  	
  	//Get the most recent VisitID given a patientID
  	public String getMaxVisitID(String patientID) {
      	Database program = new Database();
      	ResultSet output = program.query("SELECT MAX(VisitID) from Visits where PatientID = '" + patientID + "';");
        String maxVisitID = "";
          try{
              //while(output.next()){
            	  maxVisitID = output.getString(1);
              //}
              output.close();
          }
          catch (SQLException e){
              e.printStackTrace();
          }
  		return maxVisitID;
    }
  	
  	public ArrayList getMessages(String userID) {
      	Database program = new Database();
      	ResultSet output = program.query("Select * FROM Messages WHERE ToID = '"+userID+"';");
        ArrayList<String> messagelst = new ArrayList<String>();
          try{
              while(output.next()){
                  String message = output.getString("Date") + "\nFrom: " + 
                		  			output.getString("FromID") +".\n" + output.getString("Message")+ "\n\n";
                  messagelst.add(message);
              }
              output.close();
          }
          catch (SQLException e){
              e.printStackTrace();
          }
  		return messagelst;
    }
  	
    public ArrayList getDoctors() {
    	  Database program = new Database();
    	  ResultSet output = program.query("Select First, Last, ID from Doctors;");
          ArrayList<String> docNames = new ArrayList<String>();
          try{
              while(output.next()){
                  String doc = output.getString("First") + " " + output.getString("Last")+ " " + output.getString("ID");
                  docNames.add(doc);
              }
              output.close();
          }
          catch (SQLException e){
              e.printStackTrace();
          }
		return docNames;
    }
    public ArrayList getNurses() {
  	  Database program = new Database();
  	  ResultSet output = program.query("Select First, Last, ID from Doctors;");
        ArrayList<String> nurseNames = new ArrayList<String>();
        try{
            while(output.next()){
                String nurse = output.getString("First") + " " + output.getString("Last")+ " " + output.getString("ID");
                nurseNames.add(nurse);
            }
            output.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
		return nurseNames;
  }
    public void createPatient(String first, String last, String pass, int day, int month, int year,
 String address, String insurance, String email,
    		String pharmacy, String phone, String docID) throws SQLException
    {
        Database program = new Database();
       /* String first;
        String last;*/
        LocalDate date;
        LocalDate currentDate;
        //String pass;
        /*String address;
        String insurance;
        String email;
        String pharmacy;
        String phone;*/
        String doctorId;
        int age;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate;
        String command;
        String command2;
        String userCommand;
      /*  
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
                String doc = output.getString("First") + " " + output.getString("Last");
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
        */
        //"UPDATE Visits SET (Prescriptions, Notes, Summary) = ('" + prescriptions +
                //"','"+ notes + "','" + summary+"')" + "WHERE VisitID" + " = '"+ visitID + "';";
        //ResultSet output = program.query("SELECT ID  FROM Doctors WHERE First " = '" + primKeyValue + "';";
        //command2 = "SELECT ID FROM Doctors WHERE First = '" + docFirst + "';";
        //ResultSet doctorIdQuery = program.query(command2);
        
        //doctorId = output.getString("Id");
        //System.out.print(doctorId);
        doctorId = docID;
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

        userCommand = "INSERT INTO Users (FirstName, LastName, DOB, Password, ID, UserType) VALUES ('" + first +
                    "','" + last +
                    "','" + formattedDate +
                    "','" + pass +
                    "','" + program.createID(first,last,formattedDate) + "','Patient" +  "');";
        program.command(command);
        program.command(userCommand);

    }

    public void createDoctor(){
        Database program = new Database();
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
       // System.out.print(formattedDate);
        command = "INSERT INTO Doctors (First, Last, DOB, Password, ID, Expertise) VALUES ('" + first +
                "','"+ last + "','"+formattedDate + "','"+pass+"','"+program.createID(first,last,formattedDate) +
                "','"+ areaOfExpertise+"');";
        userCommand = "INSERT INTO Users (FirstName, LastName, DOB, Password, ID, UserType) VALUES ('" + first +
                "','" + last +
                "','" + formattedDate +
                "','" + pass +
                "','" + program.createID(first,last,formattedDate) + "','Doctor"+"');";
        program.command(command);
        program.command(userCommand);
    }

    public void createNurse(){
        Database program = new Database();
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
        userCommand = "INSERT INTO Users (FirstName, LastName, DOB, Password, ID, UserType) VALUES ('" + first +
                "','" + last +
                "','" + formattedDate +
                "','" + pass +
                "','" + program.createID(first,last,formattedDate) + "','Nurse" + "');";
        program.command(command);
        program.command(userCommand);
    }

    public void createVisit(String ID, String weight, String height, String temperature, String pressure, String allergies, String healthConcerns){
        Database program = new Database();
        LocalDate date;
        String prescriptions = "";
        String notes = "";
        String summary ="";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate;
        String command;
        String allergyCommand;
        String healthConcernCommand;
        String prescriptionCommand;


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
        Database run = new Database();
        String command = "UPDATE " + table;
        command += " SET " + column + " = " + "'" + newInfo + "'";
        command += " WHERE ID = " + "'" + ID + "';";
        run.command(command);
    }

    public void printTableColumn(String table, String column){
        Database run = new Database();
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
        Database run = new Database();
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

        Database run = new Database();
        ResultSet result;
        String answer;
        String command;

        command = "SELECT " + column + " FROM " + table + " WHERE " +primKey+ " = '" + primKeyValue + "';";
        result = run.query(command);
        try{
            answer = result.getString(column);
            result.close();
            return answer;        
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    public boolean authenticate(String username, String password){
        Database run = new Database();
        ArrayList<String> info = run.getUserAndPass(username);
        if (info.size() == 0) {
        	return false;
        }
        String databaseID = info.get(0);
        String databasePassword = info.get(1);
        boolean result;
        if (databaseID == null || databasePassword == null) {
        	result = false;;
        }        
        //System.out.print(databaseID + "  " + databasePassword + " ");
        else if(databaseID.equals(username) && databasePassword.equals(password)){
            result = true;
        }
        else {
        	result = false;
        }
        return result;
    }
    
    public ArrayList<String> getUserAndPass(String userID){
    	Database run = new Database();
    	ArrayList<String> answer = new ArrayList<String>();
        ResultSet result;
        String command;
        command = "SELECT ID, Password FROM Users WHERE ID = '" + userID + "';";
        result = run.query(command);
        try {
        	answer.add(result.getString("ID"));
        	answer.add(result.getString("Password"));
        	result.close();
        }catch(SQLException e) {
        	System.out.println("");
        }
        return answer; 
    }
    
    public ArrayList<String> getVisitSummaries(String id){
    	Database run = new Database();
    	ArrayList<String> patientVisits = new ArrayList<String>();
    	ResultSet result;
    	String answer;
    	String command;
    	
    	command = "SELECT Date, Summary FROM Visits WHERE PatientID =  '"+id+ "';";
    	result = run.query(command);
    	
    	try {
    		while(result.next()) {
    			answer = result.getString("Date") + "\n" + result.getString("Summary") + "\n\n";
    			patientVisits.add(answer);
    		}
    		result.close();
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return patientVisits;
    }
    public ArrayList<String> getPatientHistory(String id){
    	Database run = new Database();
    	ArrayList<String> patientVisits = new ArrayList<String>();
    	ResultSet result;
    	String answer;
    	String command;
    	
    	command = "SELECT Date, Allergies, HealthConcerns,Prescriptions, Summary FROM Visits WHERE PatientID =  '"+id+ "';";
    	result = run.query(command);
    	
    	try {
    		while(result.next()) {
    			answer = result.getString("Date") + "\n" + result.getString("Allergies") + "\n" +
    					result.getString("HealthConcerns")+ result.getString("Prescriptions") + "\n" +
    					result.getString("Summary");
    			patientVisits.add(answer);
    		}
    		result.close();
    	}
    	catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return patientVisits;
    }


}