
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.*;
import javafx.stage.*;

public class NurseUpdate {
	
	public static void display(String id, String patientID ) {
		String weightStr,heightStr,tempStr,pressStr, allergyStr, healthStr;
		Database methods = new Database();
		Stage window = new Stage();
		Scene scene1, scene2;
		
		
		
		//Layout 1
		//create main border layout for scene1
		BorderPane layout1 = new BorderPane();
		Label updateInfoLab = new Label("Update Information");
		updateInfoLab.setStyle("-fx-font-size:20;-fx-font-weight: bold");
		
		//create Vbox for left display and labels
		Label weight = new Label("Weight:");
		Label height = new Label("Height:");
		Label bodyTemp = new Label("Body Temp:");
		Label bloodPres = new Label("Blood Pressure:");
		
		//create the left box
		VBox leftBox = new VBox(20);
		leftBox.setPadding(new Insets(10,10,10,10));
		leftBox.getChildren().addAll(weight,height,bodyTemp,bloodPres);
		
		//createV Vbox to hold all text fields
		VBox textBox = new VBox(15);
		textBox.setPadding(new Insets(5,5,5,5));
		TextField weightBox = new TextField();
		TextField heightBox = new TextField();
		TextField bodyTempBox = new TextField();
		TextField bloodBox = new TextField();
		textBox.getChildren().addAll(weightBox,heightBox,bodyTempBox,bloodBox);
		
		//create HBox for button
		HBox buttonBox = new HBox(20);
		buttonBox.setPadding(new Insets(10,10,10,10));
		Button next = new Button("Next");
		BorderPane layout2 = new BorderPane();
		scene2 = new Scene(layout2, 400, 300);

		next.setOnAction(e -> window.setScene(scene2));
		buttonBox.getChildren().addAll(next);
		buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
		
		
		
		next.setOnAction(e -> {
			
			//if no input dont set anything
	        if(weightBox.getText().equals("")) {
	        	System.out.println("No Change");
	        }
	        //if there was input in field update
	        else {
	        	System.out.println("Change!");
	        }
	        
	        //if no input dont set anything
	        if(heightBox.getText().equals("")) {
	        	System.out.println("No Change");
	        }
	        //if there was input in field update
	        else {
	        	System.out.println("Change!");
	        }
	        
	        //if no input dont set anything
	        if(bodyTempBox.getText().equals("")) {
	        	System.out.println("No Change");
	        }
	        //if there was input in field update
	        else {
	        	System.out.println("Change!");
	        }
	        
	        //if no input dont set anything
	        if(bloodBox.getText().equals("")) {
	        	System.out.println("No Change");
	        }
	        //if there was input in field update
	        else {
	        	System.out.println("Change!");
	        	
	        }
            
        	window.setScene(scene2);
            
            	}
            );
		
		layout1.setTop(updateInfoLab);
		layout1.setLeft(leftBox);
		layout1.setCenter(textBox);
		layout1.setBottom(buttonBox);
		scene1 = new Scene(layout1, 400,300);
		
		//Layout 2
		
		
		Label updateInfoLab2 = new Label("Update Information");
		updateInfoLab2.setStyle("-fx-font-size:20;-fx-font-weight: bold");
		
		//create the left box
		VBox leftBox2 = new VBox(20);
		Label allergies = new Label("Allergies:");
		Label healthCon = new Label("Health Concerns:");
		leftBox2.setPadding(new Insets(10,10,10,10));
		leftBox2.getChildren().addAll(allergies, healthCon);
		
		//create textfields for allergies and healthconcerns, CHANGER CODE HERE
		VBox areaBox = new VBox();
		
		TextField allergiesArea = new TextField();
		TextField healthArea = new TextField();
		
		
		
		allergiesArea.setPrefHeight(50);
		allergiesArea.setPrefWidth(500);
		healthArea.setPrefHeight(50);
		healthArea.setPrefWidth(500);
		areaBox.getChildren().addAll(allergiesArea, healthArea);

		
		
		Button save = new Button("Save");
		save.setOnAction(e -> {
			
			//if no input dont set anything
	        if(weightBox.getText().equals("")) {
	        	System.out.println("No Change");
	        }
	        //if there was input in field update
	        else {
	        	System.out.println("Weight = " + weightBox.getText());
	        	System.out.println("Change!");
	
	        }
	        
	        //if no input dont set anything
	        if(heightBox.getText().equals("")) {
	        	System.out.println("No Change");
	        }
	        //if there was input in field update
	        else {
	        	System.out.println("Change!");
	        }
	        
	        //if no input dont set anything
	        if(bodyTempBox.getText().equals("")) {
	        	System.out.println("No Change");
	        }
	        //if there was input in field update
	        else {
	        	System.out.println("Change!");
	        }
	        
	        //if no input dont set anything
	        if(bloodBox.getText().equals("")) {
	        	System.out.println("No Change");
	        }
	        //if there was input in field update
	        else {
	        	System.out.println("Change!");
	        }
	        

	        methods.createVisit(patientID, weightBox.getText(), heightBox.getText(), bodyTempBox.getText(), bloodBox.getText(),allergiesArea.getText(),healthArea.getText());            
        	window.close();
            
            	}
            );
		
		HBox saveBox = new HBox(20);
		saveBox.setPadding(new Insets(10,10,10,10));
		saveBox.getChildren().addAll(save);
		saveBox.setAlignment(Pos.CENTER);
		
		layout2.setTop(updateInfoLab2);
		layout2.setLeft(leftBox2);
		layout2.setCenter(areaBox);
		layout2.setBottom(saveBox);
		
		window.setScene(scene1);
		window.setTitle("Update Info");
		window.show();
		
	}


}