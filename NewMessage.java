	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;


public class NewMessage {
	
	
	public static void display(String userID, String userType) {
		Stage primaryStage = new Stage();
		Database methods = new Database();
		final int width = 800;
		 final int height = 600;
		
		//1. 	Instantiating all the elements
		
		//Creating the back button 
	    Button backButton = new Button("<-- Back");
	    Label messagesLabel = new Label("New messages" );
	    Button sendButton = new Button ("Send!");
	    TextArea message = new TextArea();
		TextField to = new TextField("Type the to address");
		Label recipientLabel = new Label ("To: ");
		
		
		final ComboBox recipients = new ComboBox();
		try {
			
			if (userType == "Nurse") {
				ArrayList<String> docLst = methods.getDoctors();
				ArrayList<String> patientLst = methods.getPatients();
				recipients.getItems().addAll(FXCollections.observableArrayList(docLst,patientLst));
			}
			else if (userType == "Doctor") {
				ArrayList<String> nurseLst = methods.getNurses();
				ArrayList<String> patientLst = methods.getPatients();
				recipients.getItems().addAll(FXCollections.observableArrayList(nurseLst,patientLst));
			}
			else if(userType == "Patient") {
				ArrayList<String> docLst = methods.getDoctors();
				recipients.getItems().addAll(FXCollections.observableArrayList(docLst));

			}
			recipients.getItems().addAll("Dr.1", "Dr.2");
			
            messagesLabel.setFont(Font.font(24));
            
            //Creating a text area for messages
            message.setText("");
            
            message.setEditable(true); //Setting the textArea to not be editable.
            message.setMinHeight(300); //Setting a minumum height for the text area.
            
            //2.		Instantiating the panes
            //Instantiating the VBOX for the Messages Class. This is the ROOT pane.
            VBox messagesPortal = new VBox();
            
            //Instantiating the top part as an Hbox
            HBox toBox = new HBox();
            //toBox.setPrefHeight(100);
            toBox.getChildren().add(recipientLabel);
            toBox.getChildren().add(recipients);
            toBox.setMargin(recipientLabel, new Insets(20,20,20, 20));
            toBox.setMargin(recipients, new Insets(20,20,20, 20));

            //Instantiating the message part as an Hbox
            HBox messagePart = new HBox();
            //messagePart.setPrefHeight(100);
            messagePart.getChildren().add(message);
            
            


            //Setting up margins for the main pane
            messagesPortal.setMargin(backButton, new Insets(20, 20, 20, 20));  
            messagesPortal.setMargin(toBox, new Insets(20, 20, 20, 20));
            messagesPortal.setMargin(messagePart, new Insets(20, 20, 20, 20)); 
            messagesPortal.setMargin(sendButton, new Insets(20, 20, 20, 20)); 
            
            //Adding the nodes to the main pane
            messagesPortal.getChildren().add(backButton);
            messagesPortal.getChildren().add(toBox);
            messagesPortal.getChildren().add(messagePart);
            messagesPortal.getChildren().add(sendButton);

            
            //Setting up the spacing
            messagesPortal.setSpacing(10);
            
            
             
            Scene scene = new Scene(messagesPortal,width,height);
            //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
            
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//3.Button Handler
		
		/*class ButtonHandler implements EventHandler<ActionEvent>
		{
			public void handle(ActionEvent event)
			{
				Object source = event.getSource();
				
				//If the source is the back button
				if (source == backButton)
				{
					System.out.print("Back Button\n");
				}
				
				else if (source == sendButton)
				{
					System.out.print("sendButton\n");
				}
			}
		}
		
		*/
		//Registering the event handler
		//backButton.setOnAction(new ButtonHandler());
		backButton.setOnAction(new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent event) {
	    		Object source = event.getSource();
	    		if (source == backButton) {
	    			primaryStage.close();
	    			MessagePortal.display(userID, userType);
	    			
	    		}
	    	}
	        
	    });
		sendButton.setOnAction(new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent event) {
	    		Object source = event.getSource();
	    		if (source == sendButton) {
	    			methods.createMessage(message.getText(), userID, userID);
	    			primaryStage.close();
	    			MessagePortal.display(userID , userType);
	    			//Need Label to say Sent
	    			//Label sent = new Label();
	    			
	    			
	    		}
	    	}
	        
	    });
	}
	
}