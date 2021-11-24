
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



public class DoctorPortal{
	
	
	public static void display(String id){
		Stage primaryStage =  new Stage();

		 ArrayList<String> listDocAppt;
		 ObservableList<String> docApptData;
		 ListView docApptLV;
		 int selectedIndex;
		 String selectedAppt;
		 Label dispResult = new Label();
		 Button message = new Button();
		 Button completeVisit = new Button ("+ Complete Visit");
		 Button deleteAppt = new Button("Delete");
		 Button patientHist = new Button("History");
		 Database methods = new Database();
		//Scene scene;
		//Stage window;
		
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
	    listDocAppt = methods.getPatientsForaDoc(id);
	    //String table, String column, String primKey,String primKeyValue
	   // String appts = methods.retrieveSingleColumn("Patients", " First", "ID", id);
	   // System.out.print(appts);

	    //Adding fake names just for demonstration purpose
	    ////listDocAppt.add(" Jane Doe");
	    //listDocAppt.add("George Washington");
	    //  listDocAppt.add("first last");
	    
	    //(1)create an ObservableList from above ArrayList
	    docApptData = FXCollections.observableArrayList(listDocAppt);

	    //(2)create a ListView from above ObservableList	    
	    docApptLV = new ListView<>(docApptData);

	    //(3)set up ListView's selection mode
		 docApptLV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		 
		//initialize the selected index and selected country
		 selectedIndex = 0;
		 selectedAppt = listDocAppt.get(selectedIndex);
        
        //Label "Patients" 
        title.setStyle("-fx-font-size:18;-fx-font-weight: bold");
        
        //search bar set style 
        searchBar.setPromptText("Search");
        
        //
      
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
        completeVisit.setStyle("-fx-font-size:12");//adjust font size
        completeVisit.setMaxSize(125,20);// adjust button size (width, height)
        completeVisit.setPadding(new Insets(5,5,5,5)); 
        

        //GridPane for the top section
        GridPane northPane = new GridPane();
        northPane.setHgap(10);
        northPane.setVgap(5);
        northPane.add(title, 0, 0,10,1);
        northPane.add(completeVisit, 0, 1, 6,1);//.add(node, column,row, column span, row span)
        northPane.add(msg,29, 0,10,1);
        northPane.add(message, 37, 0);
        //northPane.add(searchBar, 22, 0);
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
	    rootPane.setCenter(docApptLV);
	    rootPane.setBottom(bottomBts);
       
	    //Message setOn Action
	    /*completeVisit.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        	DoctorVisit.display(patient);
	        }
	       });
	   */
	    
	    //Step #3: Register the buttons with its handler
	    //completeVisit.setOnAction(new ButtonHandler());
	    //deleteAppt.setOnAction(new ButtonHandler())
	    
	    deleteAppt.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        	Object source = event.getSource();
	        	//check which item is selected from the ListView
	        	int index = docApptLV.getSelectionModel().getSelectedIndex();
				String appt = listDocAppt.get(index);
	    		if (source == deleteAppt && appt!= null && index >= 0)
	 			{
	 				//remove it from the underline ArrayList AND the ObservableList
	    			listDocAppt.remove(index);
	 				docApptData.remove(index);

	 				//update the label
	 				//appt = patientNames.get(seldIndex);
	 	 			dispResult.setTextFill(Color.BLUE);
	 				dispResult.setText(appt + " is removed");
	 			}
	 			else //all other invalid actions
	 			{
					//update the label
	 				dispResult.setTextFill(Color.RED);
	 				dispResult.setText("Invalid action");
	 			}
		 	 
	        }
	    });
	    completeVisit.setOnAction(new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent event) {
	    		Object source = event.getSource();
	    		if (source == completeVisit) {
	    			//System.out.println(selectedAppt);
	    			int selectedPatientIndex = docApptLV.getSelectionModel().getSelectedIndex();
	    			if (selectedPatientIndex == -1) {
	    				dispResult.setTextFill(Color.RED);
	    				dispResult.setText("No patient was selected");
	    				return;
	    			}
	    			
	    			String selectedPatient = listDocAppt.get(selectedPatientIndex);
	    			String[]  patientDetails = selectedPatient.split(" ");
	    			DoctorVisit.display(patientDetails[0], patientDetails[1], patientDetails[2], methods, id);
	    			primaryStage.close();

	    		}
	    	}
	        
	    });
	    message.setOnAction(new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent event) {
	    		Object source = event.getSource();
	    		if (source == message) {
	    			MessagePortal.display(id, "Doctor");
	    			primaryStage.close();

	    		}
	    	}
	        
	    });
	    //Patient History Button Event Handler
	    patientHist.setOnAction(new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent event) {
	    		Object source = event.getSource();
	    		
	    		if (source == patientHist) {
	    			//System.out.println(selectedAppt);
	    			int selectedPatientIndex = docApptLV.getSelectionModel().getSelectedIndex();
	    			System.out.print(selectedPatientIndex);
	    			if (selectedPatientIndex == -1 )
	    			{
	    				dispResult.setTextFill(Color.RED);
	    				dispResult.setText("Please Select a Patient");
	    				
	    			}
	    			else {
	    			String selectedPatient = listDocAppt.get(selectedPatientIndex);
	    			String[]  patientDetails = selectedPatient.split(" ");
	    			PatientHistory.display(patientDetails[0],patientDetails[1],patientDetails[2],id, "Doctor");
		    		primaryStage.close();   
	    			}
	    		}
	    	}
	        
	    });
		 Scene scene = new Scene(rootPane, 500, 300);
		 primaryStage.setTitle("ID Here"); // ** Id of User
		 primaryStage.setScene(scene); // Place the scene in the stage
		 primaryStage.show(); // Display the stage
	    
        // Create a scene and place it in the stage
	}

	
}
