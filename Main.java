package application;
	
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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


public class Main extends Application {
	
	private final int width = 800;
	private final int height = 600;
	
	//1. 	Instantiating all the elements
	
	//Creating the back button 
    Button backButton = new Button("<-- Back");
    Label messagesLabel = new Label("Messages" );
    Button newMessageButton = new Button ("New Message");
    TextArea messages = new TextArea();
	
	public void start(Stage primaryStage) {
		try {
			
			
           
            messagesLabel.setFont(Font.font(24));
            
            //Creating a text area for messages
            messages.setText("11/12/2021 13:00. From: Doctor. Janaka\nHello patient!"
            		+ "You're doing great! The test results came just fine. Cheers!\n\n"
            		+ "10/28/2021 10:00. From: Steve (RN)\n"
            		+ "Hello Sandy. Doc. Janaka is supposed to see you today. thanks!\n\n"
            		+ "11/12/2021 13:00. From: Doctor. Janaka\nHello patient!"
            		+ "You're doing great! The test results came just fine. Cheers!\n\n"
            		+ "10/28/2021 10:00. From: Steve (RN)\n"
            		+ "Hello Sandy. Doc. Janaka is supposed to see you today. thanks!\n\n"
            		+ "11/12/2021 13:00. From: Doctor. Janaka\nHello patient!"
            		+ "You're doing great! The test results came just fine. Cheers!\n\n"
            		+ "10/28/2021 10:00. From: Steve (RN)\n"
            		+ "Hello Sandy. Doc. Janaka is supposed to see you today. thanks!\n\n"
            		+ "11/12/2021 13:00. From: Doctor. Janaka\nHello patient!"
            		+ "You're doing great! The test results came just fine. Cheers!\n\n"
            		+ "10/28/2021 10:00. From: Steve (RN)\n"
            		+ "Hello Sandy. Doc. Janaka is supposed to see you today. thanks!\n\n");
            
            messages.setEditable(false); //Setting the textArea to not be editable.
            messages.setMinHeight(350); //Setting a minumum height for the text area.
            
            //2.		Instantiating the panes
            //Instantiating the VBOX for the Messages Class. This is the ROOT pane.
            VBox messagesPortal = new VBox();
            
            //Instantiating the top part as an Hbox
            HBox topPart = new HBox();
            topPart.setPrefHeight(100);
            topPart.getChildren().add(backButton);
            
            //Instantiating the middle part as an Hbox
            HBox middlePart = new HBox();
            middlePart.setPrefHeight(100);
            middlePart.getChildren().add(messagesLabel);
            
            //An empty pane in the horizontally centered at the middle vertical pane.
            HBox middle = new HBox();
            middle.setMinWidth(550);
            
            //Adding the middle part to the middle pane
            middlePart.getChildren().add(middle);
            middlePart.getChildren().add(newMessageButton);
            


            //Setting up margins for the main pane
            messagesPortal.setMargin(topPart, new Insets(20, 20, 20, 20));  
            messagesPortal.setMargin(middlePart, new Insets(20, 20, 20, 20));
            messagesPortal.setMargin(messages, new Insets(20, 20, 20, 20)); 
            
            
            //Adding the nodes to the main pane
            messagesPortal.getChildren().add(topPart);
            messagesPortal.getChildren().add(middlePart);
            messagesPortal.getChildren().add(messages);

            
            //Setting up the spacing
            messagesPortal.setSpacing(10);
            
            
             
            Scene scene = new Scene(messagesPortal,width,height);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();
            
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//3.Button Handler
		
		class ButtonHandler implements EventHandler<ActionEvent>
		{
			public void handle(ActionEvent event)
			{
				Object source = event.getSource();
				
				//If the source is the back button
				if (source == backButton)
				{
					System.out.print("Back Button\n");
				}
				
				else if (source == newMessageButton)
				{
					System.out.print("New Message\n");
				}
			}
		}
		
		
		//Registering the event handler
		backButton.setOnAction(new ButtonHandler());
		newMessageButton.setOnAction(new ButtonHandler());
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
