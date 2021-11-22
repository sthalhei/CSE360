
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;	//**Need to import
import javafx.event.EventHandler;	//**Need to import


public class NursePortal{	
	public static void display(String id){
		Stage primaryStage = new Stage();
		Database methods = new Database();
		 ArrayList<String> apptList;//from our database 
		 ObservableList<String> apptData;
		 ListView apptLV;
		 int selectedIndex;
		 String selectedAppt;
		 Label dispResult = new Label();
		 Button message = new Button();
		 Button addAppt = new Button ("+ Add Appointment");
		 Button deleteAppt = new Button("Delete");
		 Button patientHist = new Button("History");
		 //Button editAppt	= new Button("Edit");
		 Button changeDoc = new Button("Edit Doctor");
		//instance variables
		Label title = new Label ("Welcome");
		Label msg = new Label ("Messages");
		TextField searchBar = new TextField();
		
		//Create a border pane as the root
	    BorderPane rootPane = new BorderPane();
	    rootPane.setPadding(new Insets(10, 10, 10, 10));

	    //patientlList is an ArrayList data structure, we will use it
	    //to create an ObservableList first, then from ObservableList, to
	    //create a ListView object
	    // *** We should have this ArrayList of assigned patients from 
	    // *** doctor class just populating the one below for demo
	    String doctor = methods.retrieveSingleColumn("Nurses", "Doctor", "ID", id);
	    //*******************NEED Method to retrieve all patietns whose docotr i
	    //*******************Retrieve Column is only grabbing first patient need all patients  
	    //String apptString = methods.retrieveSingleColumn("Patients", "First", "Doctor", doctor);
	    ArrayList<String> patientNames = methods.getPatients();
	    
	    apptList = new ArrayList<String>();	    
	    //Adding fake names just for demonstration purpose
	    apptList.add(" Jane Doe ");
	    apptList.add("George Washington");
	    apptList.add("first last");
	    
	    //(1)create an ObservableList from above ArrayList
	    apptData = FXCollections.observableArrayList(patientNames);

	    //(2)create a ListView from above ObservableList	    
	    apptLV = new ListView<>(apptData);

	    //(3)set up ListView's selection mode
		 apptLV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		 
		//initialize the selected index and selected country
		 selectedIndex = 0;
		 selectedAppt = patientNames.get(selectedIndex);
        
        //Label "Patients" 
        title.setStyle("-fx-font-size:18;-fx-font-weight: bold");
        
        //search bar set style 
        searchBar.setPromptText("Search");
      
        //Create Message Image
        Image image = new Image("MessageImg.jpg");
        ImageView iv = new ImageView(image);
        //setting the fit height and width of the image view 
        iv.setFitHeight(15); 
        iv.setFitWidth(15); 
     
        //Message Button Set Style
        message.setGraphic(iv);
        message.setAlignment(Pos.BASELINE_LEFT);
        msg.setAlignment(Pos.BASELINE_RIGHT);
        msg.setStyle("-fx-font-size:12");
        
        //AddAppointment Button for adding appointments
        //setting style
        addAppt.setStyle("-fx-font-size:10");//adjust font size
        addAppt.setMaxSize(125,20);// adjust button size (width, height)
        addAppt.setPadding(new Insets(5,5,5,5)); 
        

        //GridPane for the top section Positioning all elemnents
        GridPane northPane = new GridPane();
        northPane.setHgap(10);
        northPane.setVgap(5);
        northPane.add(title, 0, 0,10,1);
        northPane.add(addAppt, 0, 1, 6,1);//.add(node, column,row, column span, row span)
        northPane.add(msg,15, 0,10,1);
        northPane.add(message, 21, 0);
        northPane.add(searchBar, 22, 0);
        northPane.add(changeDoc, 22, 1);
        GridPane.setHalignment(changeDoc, HPos.RIGHT);
        northPane.add(dispResult, 7, 1,18,1);

        

        //GridPane for the top section
        FlowPane bottomBts = new FlowPane();
        bottomBts.setPadding(new Insets(10,0,0,0));
        bottomBts.setHgap(10);
        bottomBts.setVgap(6);
        bottomBts.getChildren().addAll(patientHist,deleteAppt);
        bottomBts.setAlignment(Pos.CENTER_RIGHT);
        
        
        //add panes to the root pane
        rootPane.setTop(northPane);
        rootPane.setMargin(northPane,new Insets(5,5,5,5));
	    rootPane.setCenter(apptLV);
	    rootPane.setBottom(bottomBts);
       
	    
	    //Step #3: Register the buttons with its handler
	    //addAppt.setOnAction(new ButtonHandler());
	    deleteAppt.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        	Object source = event.getSource();
		        int index = selectedIndex;
		        String appt = selectedAppt;
	    		if (source == deleteAppt && selectedAppt != null && selectedIndex >= 0)
	 			{
					//check which item is selected from the ListView
					index = apptLV.getSelectionModel().getSelectedIndex();
					appt = patientNames.get(selectedIndex);

	 				//remove it from the underline ArrayList AND the ObservableList
	 				patientNames.remove(selectedIndex);
	 				apptData.remove(selectedIndex);

	 				//update the label
	 	 			dispResult.setTextFill(Color.BLUE);
	 				dispResult.setText(selectedAppt + " is removed");
	 			}
	 			else //all other invalid actions
	 			{
					//update the label
	 				dispResult.setTextFill(Color.RED);
	 				dispResult.setText("Invalid action");
	 			}
		 	 
	        }
	    });   
	    
	    ///switch to messaging screen 
	    message.setOnAction(new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent event) {
	    		Object source = event.getSource();
	    		if (source == message) {
	    			
	    			MessagePortal.display(id, "Nurse");
	    			primaryStage.close();

	    		}
	    	}
	        
	    });
	    addAppt.setOnAction(new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent event) {
	    		Object source = event.getSource();
	    		
	    		if (source == addAppt) {
	    			//System.out.println(selectedAppt);
	    			int selectedPatientIndex = apptLV.getSelectionModel().getSelectedIndex();
	    			String selectedPatient = patientNames.get(selectedPatientIndex);
	    			String[]  patientDetails = selectedPatient.split(" ");
	    			String ageS;
	    			ageS = methods.retrieveSingleColumn("Patients", "Age", "ID", patientDetails[2]);
	    			int ageI = Integer.parseInt(ageS);
	    			NurseUpdate.display(id,patientDetails[2], ageI);
		    		primaryStage.close();    			

	    		}
	    	}
	        
	    });
	    
        // Create a scene and place it in the stage
	    Scene scene = new Scene(rootPane, 500, 300);
	    primaryStage.setTitle("ID Here"); // ** Id of User
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	}
	 //Step #2: Create a ButtonHandler class
 /*   private class ButtonHandler implements EventHandler<ActionEvent>
   	 {
   	    //Override the abstact method handle()
   	    public void handle(ActionEvent e)
        {
			Object source = e.getSource();

			//
            if(source == addAppt && tfAppts.getText().length() >0)
            {
				String newAppt = tfAppts.getText().trim();//just example 

 				//update the underline ArrayList AND the ObservableList
 				apptList.add(newAppt);
 				apptData.add(newAppt);;

 				//update the top label
 				dispResult.setTextFill(Color.BLUE);
 				dispResult.setText(newAppt + " is added inside the list.");

 				//clear the text field input
 			 	tfAppts.clear();
  			}
 			
 			if (source == deleteAppt && selectedAppt != null && selectedIndex >= 0)
 			{
				//check which item is selected from the ListView
				selectedIndex = apptLV.getSelectionModel().getSelectedIndex();
				selectedAppt = apptList.get(selectedIndex);

 				//remove it from the underline ArrayList AND the ObservableList
 				apptList.remove(selectedIndex);
 				apptData.remove(selectedIndex);

 				//update the label
 	 			dispResult.setTextFill(Color.BLUE);
 				dispResult.setText(selectedAppt + " is removed");
 			}
 			else   //all other invalid actions
 			{
				//update the label
 				dispResult.setTextFill(Color.RED);
 				dispResult.setText("Invalid action");
 			}
	 	 } //end of handle()
     }//end of ButtonHandler class
	
	public static void main(String[] args)
	  {
	      launch(args);
	  }*/
}
