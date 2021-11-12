//import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Main extends Application {
	
	private ArrayList<String> apptList;
	private ObservableList<String> apptData;
	private ListView apptLV;
	private ArrayList<String> listDocAppt;
	private ObservableList<String> docApptData;
	private ListView docApptLV;
	private int selectedIndex;
	private String selectedAppt;
	private Label dispResult = new Label();
	private Button btnMessage = new Button();
	private Button btnAddAppt = new Button ("+ Add Appointment");
	private Button btnDeleteAppt = new Button("Delete");
	private Button btnPatientHist = new Button("History");
	private Button btnEditAppt	= new Button("Edit");
	private Button btnChangeDoc = new Button("Edit Doctor");
	private Button btnCompltVst = new Button ("+ Complete Visit");
	//For AddAppointment Method
	private ArrayList<String> docList;
	TextField tfFName = new TextField();
	TextField tfLName = new TextField();
	private String doctorPicked;
	private String getFName;
	private String getLName;
	private ComboBox<String> cbDoctor;
	private Button btnSubmit;
	private Popup popupError ;
	private Stage primaryStage;
	private Label errorMsg = new Label (" Please Login ");
	
	
	private Stage window; 
	Scene sceneLogin, sceneFrgtPass, sceneDoc, sceneNurse,
		  sceneAddAppt, sceneNewAcct;
	
	public static void main(String[] args) {	
			launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		loginWindow();
		//forgotPasswordWindow();
		nurseWindow();
		//doctorWindow();
		//addApptWindow();
	    
	    window.setTitle("User Login"); // Set the stage title
	    window.setScene(sceneLogin); // Login in will always be the first window to any user
	    window.show(); // Display the stage
	}
	public void loginWindow (){
		//Create a border pane as the root
	    BorderPane rootPane = new BorderPane();
	    
	    //CenterPane to hold text Fields
	    GridPane centerPane = new GridPane();
	    centerPane.setAlignment(Pos.CENTER);
	    centerPane.setPadding(new Insets(10, 10, 10, 10));
	    centerPane.setHgap(5);
	    centerPane.setVgap(10);

	    //Create text fields
	    TextField Login = new TextField();
	    Login.setPromptText("Username");
	    PasswordField Password = new PasswordField();
	    Password.setPromptText("Password");
	    
	    //Add Textfields to centerpane
	    centerPane.setVgap(15);
	    centerPane.add(Login, 1, 0);
	    centerPane.add(Password, 1, 1);
	    
	    //southPane is a horizontal TilePane, it contains 3 buttons
		TilePane southPane = new TilePane(Orientation.HORIZONTAL);
		southPane.setAlignment(Pos.CENTER);
		
		//create three buttons
	    Button btnLogin = new Button("Login");
	    Button btnFrgtPass = new Button("Forgot Password");
	    Button btnNewAcct = new Button("New Account");
	    
	    //Set Text size of buttons
	    btnLogin.setStyle("-fx-font-size:10");
	    btnFrgtPass.setStyle("-fx-font-size:10");
	    btnNewAcct.setStyle("-fx-font-size:10");

		//To enable the buttons to be resized
	    btnLogin.setMaxSize(100,25);
		btnFrgtPass.setMaxSize(100,25);
		btnNewAcct.setMaxSize(90,25);
		
		//Button setOnAction
		btnFrgtPass.setOnAction(e -> window.setScene(sceneFrgtPass));
		//Login Button Pressed 
		btnLogin.setOnAction(e -> window.setScene(sceneNurse));
		
		//set the horizontal gap between above 3 buttons 
		southPane.setHgap(10.0);
		southPane.setVgap(10.0);
		southPane.getChildren().addAll(btnLogin,btnFrgtPass,btnNewAcct);
	    southPane.setPadding(new Insets(8,8,8,8));//padding to the edge of the box
		
		//Place centerPane and southPane inside the rootPane
	    rootPane.setCenter(centerPane);
	    rootPane.setBottom(southPane);
	    
	    //Set scene
	    sceneLogin = new Scene(rootPane, 300, 150);   
	   
	}
	public void forgotPasswordWindow() {
		//Create a border pane as the root
	    BorderPane rootPane = new BorderPane();
	    
	    //CenterPane to hold text Fields
	    GridPane centerPane = new GridPane();
	    centerPane.setAlignment(Pos.CENTER);
	    centerPane.setPadding(new Insets(5,5,5,5));
	    centerPane.setHgap(5);
	    centerPane.setVgap(5);

	   //Create text fields
	    TextField username = new TextField();
	    username.setPromptText("Enter Username");
	    TextField Password1 = new PasswordField();
	    Password1.setPromptText("New Password");
	    TextField Password2 = new PasswordField();
	    Password2.setPromptText("Enter Password Again");

	   
	    //Add Textfields to centerpane
	    centerPane.setVgap(15);
	    centerPane.add(username, 1, 0);
	    centerPane.add(Password1, 1, 1);
	    centerPane.add(Password2, 1,2);
	    
	    //southPane is a horizontal TilePane, it contains 3 buttons
		GridPane southPane = new GridPane();
		southPane.setAlignment(Pos.CENTER);
		
		//create three buttons
	    Button btnDone = new Button("Done");
	    
	    //Set Text size 
	    btnDone.setStyle("-fx-font-size:10");
	    
		//To enable the buttons to be resized
	    btnDone.setMaxSize(125,50);
	    
	    //setOnAction
	    //If done is presses return to Login window
		btnDone.setOnAction(e -> window.setScene(sceneLogin));
		
		//set the horizontal gap between above 3 buttons 
		southPane.setHgap(10.0);
		southPane.setVgap(2.0);
		southPane.getChildren().addAll(btnDone);
	    southPane.setPadding(new Insets(5,5,5,5));//padding to the edge of the box
		
		//Place centerPane and southPane inside the rootPane
	    rootPane.setCenter(centerPane);
	    rootPane.setBottom(southPane);
	    
	    // Create a scene and place it in the stage
	    sceneFrgtPass = new Scene(rootPane, 250, 150);
	}
	public void nurseWindow() throws FileNotFoundException {
		
	    //instance variables
		Label title = new Label ("Welcome");
		Label msg = new Label ("Messages");
		TextField searchBar = new TextField();
		
		//Create a border pane as the root
	    BorderPane NrootPane = new BorderPane();
	    NrootPane.setPadding(new Insets(10, 10, 10, 10));

	    //patientlList is an ArrayList data structure, we will use it
	    //to create an ObservableList first, then from ObservableList, to
	    //create a ListView object
	    // *** We should have this ArrayList of assigned patients from 
	    // *** doctor class just populating the one below for demo
	    apptList = new ArrayList<String>();	    
	    //Adding fake names just for demonstration purpose
	    apptList.add(" Jane Doe ");
	    apptList.add("George Washington");
	    apptList.add("first last");
	    
	    //(1)create an ObservableList from above ArrayList
	    apptData = FXCollections.observableArrayList(apptList);

	    //(2)create a ListView from above ObservableList	    
	    apptLV = new ListView<>(apptData);

	    //(3)set up ListView's selection mode
		 apptLV.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		 
		//initialize the selected index and selected country
		 selectedIndex = 0;
		 selectedAppt = apptList.get(selectedIndex);
        
        //Label "Patients" 
        title.setStyle("-fx-font-size:18;-fx-font-weight: bold");
        
        //search bar set style 
        searchBar.setPromptText("Search");
      
        //Create Message Image
        Image image = new Image(new FileInputStream("MessageImg.jpg"));
        ImageView iv = new ImageView(image);
        //setting the fit height and width of the image view 
        iv.setFitHeight(15); 
        iv.setFitWidth(15); 
     
        //Message Button Set Style
        btnMessage.setGraphic(iv);
        btnMessage.setAlignment(Pos.BASELINE_LEFT);
        msg.setAlignment(Pos.BASELINE_RIGHT);
        msg.setStyle("-fx-font-size:12");
        
        //AddAppointment Button for adding appointments
        //setting style
        btnAddAppt.setStyle("-fx-font-size:10");//adjust font size
        btnAddAppt.setMaxSize(125,20);// adjust button size (width, height)
        btnAddAppt.setPadding(new Insets(5,5,5,5)); 
        //SetOnAction
        //have the 
        btnAddAppt.setOnAction(e -> window.setScene(sceneAddAppt));
        
        //GridPane for the top section Positioning all elemnents
        GridPane northPane = new GridPane();
        northPane.setHgap(10);
        northPane.setVgap(5);
        northPane.add(title, 0, 0,10,1);
        northPane.add(btnAddAppt, 0, 1, 6,1);//.add(node, column,row, column span, row span)
        northPane.add(msg,15, 0,10,1);
        northPane.add(btnMessage, 21, 0);
        northPane.add(searchBar, 22, 0);
        northPane.add(btnChangeDoc, 22, 1);
        GridPane.setHalignment(btnChangeDoc, HPos.RIGHT);
        northPane.add(dispResult, 7, 1,18,1);

        //GridPane for the top section
        FlowPane bottomBts = new FlowPane();
        bottomBts.setPadding(new Insets(10,0,0,0));
        bottomBts.setHgap(10);
        bottomBts.setVgap(6);
        bottomBts.getChildren().addAll(btnEditAppt,btnPatientHist,btnDeleteAppt);
        bottomBts.setAlignment(Pos.CENTER_RIGHT);
          
        //add panes to the root pane
        NrootPane.setTop(northPane);
        NrootPane.setMargin(northPane,new Insets(5,5,5,5));
	   NrootPane.setCenter(apptLV);
	    NrootPane.setBottom(bottomBts);
        
	    //Step #3: Register the buttons with its handler
	    //addAppt.setOnAction(new ButtonHandler());
	    btnDeleteAppt.setOnAction(new ButtonHandler()); 	    
	    
        // Create a scene and place it in the stage
	    sceneNurse = new Scene(NrootPane, 500, 300);
	}
	private class ButtonHandler implements EventHandler<ActionEvent>
  	 {
  	    //Override the abstact method handle()
  	    public void handle(ActionEvent e)
       {
			Object source = e.getSource();

			/*//if user enter a new country name and press the "Add" button
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
 			}*/
			
			if (source == btnDeleteAppt && selectedAppt != null && selectedIndex >= 0)
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
	public void doctorWindow () {
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
		    listDocAppt = new ArrayList<String>();
		    
		    //Adding fake names just for demonstration purpose
		    listDocAppt.add(" Jane Doe");
		    listDocAppt.add("George Washington");
		    listDocAppt.add("first last");
		    
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
	        btnMessage.setGraphic(iv);
	        btnMessage.setAlignment(Pos.BASELINE_LEFT);
	        msg.setAlignment(Pos.BASELINE_RIGHT);
	        msg.setStyle("-fx-font-size:12");
	        
	        //AddAppointment Button for adding appointments
	        //setting style
	        btnCompltVst.setStyle("-fx-font-size:12");//adjust font size
	        btnCompltVst.setMaxSize(125,20);// adjust button size (width, height)
	        btnCompltVst.setPadding(new Insets(5,5,5,5)); 
	        
	        //GridPane for the top section
	        GridPane northPane = new GridPane();
	        northPane.setHgap(10);
	        northPane.setVgap(5);
	        northPane.add(title, 0, 0,10,1);
	        northPane.add(btnCompltVst, 0, 1, 6,1);//.add(node, column,row, column span, row span)
	        northPane.add(msg,15, 0,10,1);
	        northPane.add(btnMessage, 21, 0);
	        northPane.add(searchBar, 22, 0);
	        northPane.add(dispResult, 7, 1,18,1);
	        
	        //GridPane for the top section
	        FlowPane bottomBts = new FlowPane();
	        bottomBts.setPadding(new Insets(10,0,0,0));
	        bottomBts.setHgap(10);
	        bottomBts.setVgap(6);
	        bottomBts.getChildren().addAll(btnPatientHist,btnDeleteAppt);
	        bottomBts.setAlignment(Pos.CENTER_RIGHT);
	        
	        //add panes to the root pane
	        rootPane.setTop(northPane);
	        rootPane.setMargin(northPane,new Insets(5,5,5,5));
		    rootPane.setCenter(docApptLV);
		    rootPane.setBottom(bottomBts);
		    
		    //Step #3: Register the buttons with its handler
		    //completeVisit.setOnAction(new ButtonHandler());
		    btnDeleteAppt.setOnAction(new ButtonHandler()); 			    
		    
	        // Create a scene and place it in the stage
		    sceneDoc = new Scene(rootPane, 500, 300);
	}
	public void addApptWindow() {

		Label firstName = new Label ("First :");
		Label lastName = new Label ("Last :");
		Label assgDoctor = new Label ("Assigned Doctor :");
		Label title = new Label ("New Appointment");
		
		Button sumbit = new Button ("Sumbit");
		
		
		//Create a border pane as the root
	    BorderPane rootPane = new BorderPane();
	    rootPane.setPadding(new Insets(5,5,5,5));
	    	    
	    //CenterPane to hold text Fields
	    GridPane centerPane = new GridPane();
	    centerPane.setAlignment(Pos.CENTER);
	    centerPane.setPadding(new Insets(10, 10, 10, 10));
	    centerPane.setHgap(5);
	    centerPane.setVgap(10);

	   //Create text fields
	    tfFName.setPromptText("First Name ");
	    tfLName.setPromptText("Last Name ");
	    
	    
	    //Title Label style
	    GridPane top = new GridPane();
	    top.setPadding(new Insets(10, 10, 10, 10));
        title.setStyle("-fx-font-size:12;-fx-font-weight: bold");
        title.setAlignment(Pos.BOTTOM_LEFT);
        top.add(title, 0,0);
        top.add(errorMsg, 1, 0);

	    // We would already have a doctor list existing this is for demo 
        // NEED TO LINK DOCOTOR LIST HERE 
	    docList = new ArrayList<String>();
	    docList.add("Dr.Thomas");
	    docList.add("Dr.Gregor");
	    docList.add("Dr.Johnson");

	    cbDoctor = new ComboBox<String>();
	    cbDoctor.getItems().addAll(FXCollections.observableArrayList(docList));
	    cbDoctor.setValue("Doctor");//default string that shows 
		
		//create submit button
	    btnSubmit = new Button("Submit");
	    //Set Text size 
	    btnSubmit.setStyle("-fx-font-size:10");

		//set the horizontal gap between above 3 buttons 
		centerPane.add(firstName, 0, 0);
		centerPane.add(lastName, 0, 1);
		centerPane.add(assgDoctor, 0, 2);
		centerPane.add(tfFName, 1, 0);
		centerPane.add(tfLName, 1, 1);
		centerPane.add(cbDoctor, 1, 2);
		
		centerPane.setAlignment(Pos.CENTER_LEFT);
		
		//Place centerPane and southPane inside the rootPan
		rootPane.setTop(top);
	    rootPane.setCenter(centerPane);
	    rootPane.setBottom(sumbit);
	    rootPane.setAlignment(sumbit, Pos.CENTER);

	    //SetOnAction
	    btnSubmit.setOnAction(e-> window.setScene(sceneNurse));
	       
	    // Create a scene and place it in the stage
	    sceneAddAppt = new Scene(rootPane, 300, 200);
	}
}
