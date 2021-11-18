import java.awt.Font;
import java.awt.ScrollPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.JTextArea;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Orientation;
import javafx.scene.layout.Pane;

public class PatientView{
	public static void display(String userID) {
		
		Stage window =  new Stage();
		
		//new DataBase connection
		Database methods = new Database();
		
		//THIS CAN ALL BE A SCENE
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		//Create a border pane as the root
	    BorderPane rootPane = new BorderPane();

        //GridPane for the top section
        GridPane northPane = new GridPane();
        northPane.setHgap(10);
        northPane.setVgap(5);
        //Label "Patients" 
        Label title = new Label ("Welcome!");
        title.setStyle("-fx-font-size:20;-fx-font-weight: bold");
        
        
        //Create Message Image
        //Image image = new Image(new FileInputStream("src\\MessageImg.jpg"));
        //ImageView iv = new ImageView(image);
        //setting the fit height and width of the image view 
        //iv.setFitHeight(15); 
        //iv.setFitWidth(15); 
     
        //Message Button
        //Button message = new Button();
        //message.setGraphic(iv);
        //message.setAlignment(Pos.BASELINE_RIGHT);
        
        // Button for adding appointments
        Button newMessage = new Button ("New Message");
        newMessage.setStyle("-fx-font-size:12");//adjust font size
        newMessage.setMaxSize(150,25);// adjust button size (width, height)
        newMessage.setPadding(new Insets(5,5,5,5));
        
        //CHANGE CODE HERE TO CONNECT TO ALI'S UI
        newMessage.setOnAction(e -> {
        	System.out.println("Connect to Ali's Message UI");
        });
     
        
        //create an empty node to space the message button
        Label spacer = new Label("                                                                                                       ");
        spacer.minHeightProperty().bind(title.heightProperty());
        
        //add components to the top pane
        northPane.add(title, 0, 0);
        //northPane.add(message, 1, 0);
        northPane.add(spacer, 2, 0);
        northPane.add(newMessage, 3, 0);//.add(node, column,row, column span, row span)
        
       
        
        //create a gridpane to hold patient info and set padding
        GridPane patientGrid = new GridPane();
        patientGrid.setPadding(new Insets(10, 10, 10, 10));
        patientGrid.setVgap(8);
        patientGrid.setHgap(8);
        
        
        //FOR THE INFO GRID
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
        //CHANGE CODE HERE TO GET INFO
        //create variable to fill in each text field with info of patient
        String fName = methods.retrieveSingleColumn("Patients", "First", "ID", userID);
        String lName = methods.retrieveSingleColumn("Patients", "Last", "ID", userID);
        String DOB = methods.retrieveSingleColumn("Patients", "DOB", "ID", userID);
        String emailVar = methods.retrieveSingleColumn("Patients", "Email", "ID", userID);
        String phoneNum = methods.retrieveSingleColumn("Patients", "Phone", "ID", userID);
        String addressVar = methods.retrieveSingleColumn("Patients", "Address", "ID", userID);
        String insuranceVar = methods.retrieveSingleColumn("Patients", "Insurance", "ID", userID);
        String policyNumVar = "#10293";
        String pharmacyVar = methods.retrieveSingleColumn("Patients", "Pharmacy", "ID", userID);
        
        //create the labels for the user that is logged in and add to 0,0 (First Column, First Row)
        TextField firstName = new TextField();
        firstName.setPromptText(fName);
        GridPane.setConstraints(firstName, 0, 0);
        //create the labels for the user that is logged in and add to 0,0 (First Column, Second Row)
        TextField lastName = new TextField();
        lastName.setPromptText(lName);
        GridPane.setConstraints(lastName, 0, 1);
        //create the labels for the user that is logged in and add to 0,0 (First Column, Third Row)
        TextField dateOB = new TextField();
        dateOB.setPromptText(DOB);
        GridPane.setConstraints(dateOB, 0, 2);
        
        //create the labels for the user that is logged in and add to 0,0 (Second Column, First Row)
        TextField email = new TextField();
        email.setPromptText(emailVar);
        GridPane.setConstraints(email, 1, 0);
        //create the labels for the user that is logged in and add to 0,0 (Second Column, Second Row)
        TextField phone= new TextField();
        phone.setPromptText(phoneNum);
        GridPane.setConstraints(phone, 1, 1);
        //create the labels for the user that is logged in and add to 0,0 (Second Column, Third Row)
        TextField address = new TextField();
        address.setPromptText(addressVar);
        GridPane.setConstraints(address, 1, 2);
        
        //create the labels for the user that is logged in and add to 0,0 (Third Column, First Row)
        TextField insurance = new TextField();
        insurance.setPromptText(insuranceVar);
        GridPane.setConstraints(insurance, 2, 0);
        //create the labels for the user that is logged in and add to 0,0 (Third Column, Second Row)
        TextField policyNum = new TextField();
        policyNum.setPromptText(policyNumVar);
        GridPane.setConstraints(policyNum, 2, 1);
        //create the labels for the user that is logged in and add to 0,0 (Third Column, Second Row)
        TextField insuranceAddress = new TextField();
        insuranceAddress.setPromptText(pharmacyVar);
        GridPane.setConstraints(insuranceAddress, 2, 2);
        
        
        //create an update button
        Button updateButt = new Button ("Update");
        updateButt.setStyle("-fx-font-size:12");//adjust font size
        updateButt.setMaxSize(150,25);// adjust button size (width, height)
        updateButt.setPadding(new Insets(5,5,5,5));
       
        //EVENT HANDELER FOR UPDATE (CHANGE CODE HERE)
        ///////////////////////////////////////////////////////
        updateButt.setOnAction(e -> {
        
        	 //First Name Text Field
            //if no input dont set anything
            if(firstName.getText().equals("")) {
            	System.out.println("No Change");
            }
            //if there was input in field update
            else {
            	methods.updateUser("Patients", userID, "First", firstName.getText());
            	methods.updateUser("Users", userID, "FirstName", firstName.getText());
            }
            
            
            //Last Name Text Field
            //if no input dont set anything
            if(lastName.getText().equals("")) {
            	System.out.println("No Change");
            }
            //if there was input in field update
            else {
            	methods.updateUser("Patients", userID, "Last", lastName.getText());
            	methods.updateUser("Users", userID, "LastName", lastName.getText());
            }
            
            
            //DOB Name Text Field
            //if no input dont set anything
            if(dateOB.getText().equals("")) {
            	System.out.println("No Change");
            }
            //if there was input in field update
            else {
            	methods.updateUser("Patients", userID, "DOB", dateOB.getText());
            	methods.updateUser("Users", userID, "DOB", dateOB.getText());
            }
        
        
        //Email Name Text Field
        //if no input dont set anything
        if(email.getText().equals("")) {
        	System.out.println("No Change");
        }
        //if there was input in field update
        else {
        	methods.updateUser("Patients", userID, "Email", email.getText());
        }
        
        //Phone Num Text Field
        //if no input dont set anything
        if(phone.getText().equals("")) {
        	System.out.println("No Change");
        }
        //if there was input in field update
        else {
        	methods.updateUser("Patients", userID, "Phone", phone.getText());
        }
        
        
        //Address Name Text Field
        //if no input dont set anything
        if(address.getText().equals("")) {
        	System.out.println("No Change");
        }
        //if there was input in field update
        else {
        	methods.updateUser("Patients", userID, "Address", address.getText());
        }
        
        
        //Insurance Name Text Field
        //if no input dont set anything
        if(insurance.getText().equals("")) {
        	System.out.println("No Change");
        }
        //if there was input in field update
        else {
        	methods.updateUser("Patients", userID, "Insurance", insurance.getText());
        }
        
        //Policy Num Text Field
        //if no input dont set anything
        if(policyNum.getText().equals("")) {
        	System.out.println("No Change");
        }
        //if there was input in field update
        else {
        	
        }
        
        //InsuranceAddy Text Field
        //if no input dont set anything
        if(address.getText().equals("")) {
        	System.out.println("No Change");
        }
        //if there was input in field update
        else {
        	methods.updateUser("Patients", userID, "Address", address.getText());
        }
        
        //open an update window
        //UpdateAlert.display();
        
        	}
        );
        
        //Redirect to Message Interface
        newMessage.setOnAction(new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent event) {
	    		Object source = event.getSource();
	    		if (source == newMessage) {
	    			
	    			MessagePortal.display(userID, "Patient");
	    			window.close();

	    		}
	    	}
	        
	    });
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
       
        //create a VBox to hold button
        VBox updateVBox = new VBox(25);
        updateVBox.getChildren().addAll(updateButt);
        updateVBox.setPadding(new Insets(75,40,15,15));
 
        
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        
        //REQUEST APPOINTMENT BUTTON CODE
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        //create Request Appointment Button
        Button requestApp = new Button ("Request an Appointment");
        requestApp.setStyle("-fx-font-size:12");//adjust font size
        requestApp.setMaxSize(150,25);// adjust button size (width, height)
        requestApp.setPadding(new Insets(5,5,5,5));
        
        //EVENT HANDLER FOR REQUEST BUTTON (CHANGE CODE HERE)
        requestApp.setOnAction(e -> {
            
        	System.out.println("Request here!");
        	
        	ClassAboutNothing.requestAppointment();
        	
        	//RequestAlert.display();
            
            	}
            );
        
        
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        //create a HBox to hold button and Change Doctor and submit
        HBox appHBox = new HBox(25);
        
        
        /////////////////////////////THIS IS FOR DROPDOWN TO SELECT DOCTOR
        //create dropdown box for all doctors 
        ArrayList<String> docList = methods.getDoctors();
        ObservableList<String> obserDocList = FXCollections.observableArrayList(docList);
        
        ComboBox<String> doctorChoice = new ComboBox<>();
        //WE NEED TO FIND OUT HOW TO ADD DOCTORS FROM ARRAY TO THE DROP DOWN, (FOR LOOP MAYBE?) CHANGE CODE HERE
        doctorChoice.setItems(obserDocList);
        //set default for drop down (CALL METHOD) CHANGE CODE HERE
        doctorChoice.setValue(methods.retrieveSingleColumn("Patients", "Doctor", "ID", userID));
        ////////////////////////////////////////////////////////////////////////////////
        
        
        //create button to submit which doctor
        Button doctorChange = new Button("Submit for new Doctor");
        //EVENT HANDLER FOR THE DOCTOR CHANGE
        doctorChange.setOnAction(e -> {
        	//CHANGE TO METHOD TO SET PATIENT'S DOCTOR METHOD CHANGE CODE HERE
        	methods.updateUser("Patients", userID, "Doctor", doctorChoice.getValue());
        });
        
        appHBox.getChildren().addAll(doctorChoice, doctorChange,requestApp);
        appHBox.setPadding(new Insets(0,15,15,15));
        GridPane.setConstraints(appHBox, 0, 4, 4, 1); //(node, column,row, column span, row span)
        
        
        //add all the textfields into the gridpane
        patientGrid.getChildren().addAll(firstName,lastName,dateOB,email,phone,address,insurance,policyNum,insuranceAddress,appHBox);
        
        
        
        //create Gridpane for bottom of screen
        GridPane visitHis = new GridPane();
        //create Label
        Label visitHisLab = new Label("Visit History");
        visitHisLab.setStyle("-fx-font-size:20;-fx-font-weight: bold");
        GridPane.setConstraints(visitHis, 0, 0, 3, 1);
        
        //////////////////////////////////////////////////////////////////////////////////////////////
        //Figure out how to show all the previous vists for Patient CHANGE CODE HERE
        //createJTextArea for history
        ArrayList<String> summaries = methods.getVisitSummaries(userID);
        
        String text="";
        for(String object:summaries) {
        	text+=object;
        }
    
        
        
        TextArea history = new TextArea(text);
        
        
        ////////////////////////////////////////////////////////////////////////////////////////////////
        
        history.setPrefHeight(50);
        history.setPrefWidth(500);
        //create HBox
        HBox histHBox = new HBox(10);
        histHBox.setPadding(new Insets(0,5,5,5)); //insets (Top,Right,Bottom,Left)
        histHBox.getChildren().addAll(history);
        history.setEditable(false);
        GridPane.setConstraints(histHBox, 0, 2, 3, 1);
        
        //add it all to visitgrid
        visitHis.getChildren().addAll(visitHisLab,histHBox);
        
        
        //add panes to the root pane
        rootPane.setTop(northPane);
	    rootPane.setCenter(patientGrid);
	    rootPane.setRight(updateVBox);
	    rootPane.setBottom(visitHis);
       
        // Create a scene and place it in the stage
	    Scene scene = new Scene(rootPane, 600, 300);
	    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	    
	    window.setScene(scene);
	    window.show();
	    
	}
}