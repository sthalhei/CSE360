import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Login extends Application {
	//Main stage
	private Stage window;
	private Scene sceneLogin, sceneNewAcct;
	//create three buttons
    Button btnLogin = new Button("Login");
    Button btnNewAcct = new Button("New Account");
    Button registerBt;
    Database methods = new Database(); 
    TextField firstName, lastName, phoneNumber, email, pharmacy, address, insurance,pass, Login;
    PasswordField Password;
    ComboBox cbYear, cbDay, cbMonth, cbDoctors;
    
    
    //###############Adding the doctor and patient radiobuttons & new text field
    RadioButton doctorRB, patientRB;
    ToggleGroup userTypeGroup = new ToggleGroup();
    TextField expertise;
    
    public Database getDatabase() {
    	return methods;
    }
	public void start (Stage primaryStage) {
	
		
		//############### Defining the radio buttons & a new textfield
		doctorRB = new RadioButton("Doctor");
		patientRB = new RadioButton("Patient");
		doctorRB.setToggleGroup(userTypeGroup);
		patientRB.setToggleGroup(userTypeGroup);
		expertise = new TextField();
		
		
	    BorderPane loginRoot = new BorderPane();
	    
	    //CenterPane to hold text Fields
	    GridPane centerPane = new GridPane();
	    centerPane.setAlignment(Pos.CENTER);
	    centerPane.setPadding(new Insets(10, 10, 10, 10));
	    centerPane.setHgap(5);
	    centerPane.setVgap(10);

	    //Create text fields
	    Login = new TextField();
	    Login.setPromptText("Username");
	    Password = new PasswordField();
	    Password.setPromptText("Password");
	    
	    //Add Textfields to centerpane
	    centerPane.setVgap(15);
	    centerPane.add(Login, 1, 0);
	    centerPane.add(Password, 1, 1);
	    
	    //southPane is a horizontal TilePane, it contains 3 buttons
		TilePane southPane = new TilePane(Orientation.HORIZONTAL);
		southPane.setAlignment(Pos.CENTER);
		
		
	    
	    //Set Text size of buttons
	    btnLogin.setStyle("-fx-font-size:10");
	    btnNewAcct.setStyle("-fx-font-size:10");

		//To enable the buttons to be resized
	    btnLogin.setMaxSize(100,25);
		btnNewAcct.setMaxSize(90,25);
		
		//Button setOnAction
		//btnNewAcct.setOnAction(e -> window.setScene(sceneNewAcct));
		//btnNewAcct.setOnAction(new ButtonHandler());
		//Login Button Pressed 
		//btnLogin.setOnAction(e -> window.setScene(sceneNurse));
		
		//set the horizontal gap between above 3 buttons 
		southPane.setHgap(10.0);
		southPane.setVgap(10.0);
		southPane.getChildren().addAll(btnLogin,btnNewAcct);
	    southPane.setPadding(new Insets(8,8,8,8));//padding to the edge of the box
		
		//Place centerPane and southPane inside the loginRoot
	    loginRoot.setCenter(centerPane);
	    loginRoot.setBottom(southPane);
	    
	    //Set scene
	    sceneLogin = new Scene(loginRoot, 300, 150);   
	    
	    /////////////////////////// New Account
	    
		//Create a border pane as the root pane of window
	    BorderPane newAcctRoot = new BorderPane();
	    newAcctRoot.setPadding(new Insets(12, 12, 12, 12));
	
		//Header Text of the Window 
	    Label title = new Label("Create Account");
	    title.setStyle("-fx-font-size:15;-fx-font-weight: bold");
	    
	    
	    //first name field
	    firstName = new TextField();
	    firstName.setPromptText("First Name");
	    //last name field
	    lastName = new TextField();
	    lastName.setPromptText("Last Name");
	    //phoneNumber field
	     phoneNumber = new TextField();
	    phoneNumber.setPromptText("Phone Number");
	    //email field
	    email = new TextField();
	    email.setPromptText("Email");
	    //Address fields
	    address = new TextField();
	    address.setPromptText("Address");
	    //Insurance field
	    insurance = new TextField();
	    insurance.setPromptText("Insurance Name");
	    //Name of pharmacy field
	    pharmacy = new TextField();
	    pharmacy.setPromptText("Pharmacy Name");
	    //Enter Pssword field
	    pass = new TextField();
	    pass.setPromptText("Password");
	    
	    
	    /* 
	     * Arrays created for populating comboBoxes
	     * monthArr (months) , dayArr(days) , yearArr(years)
	     * hold integers for selecting DOB . 
	    */
	    // monthArr holds integerS 1-12 for Months
	    String [] monthArr = new String[12];
	    for ( int i = 0; i < 12; i++) {
	    	String num =Integer.toString(i+1);
	    	if (i<9)
	    		monthArr[i] = "0" + num;// if single digit shows as 01, 02 etc. 
	    	else
	    		monthArr[i] = num;		
	    }
	    // daysArr holds integers 1-31 for days of month
	    String [] dayArr = new String[31];
	    for ( int i = 0; i < 31; i++) {
	    	String num =Integer.toString(i+1);
	    	if (i<9)
	    		dayArr[i] = "0" + num;// if single digit shows as 01, 02 etc. 
	    	else
	    		dayArr[i] = num;		
	    } 
	    //yearArr holds integers 1910 - 2003 for years
	    String [] yearArr = new String[95];
	    for ( int i = 0; i < 95 ; i++) {
	    	String num =Integer.toString(1910+i+1);
	    	yearArr[i] = num;		
	    }
	    
	    //ComboBox created, for DOB selection
	    
	    //Create month ComboBox and populate with monthArr
	    cbMonth = new ComboBox();//Month ComboBox
	    cbMonth.getItems().addAll(FXCollections.observableArrayList(monthArr));
	    cbMonth.setValue("Month");//default string that shows 
	
	    
	    //create day ComboBox and add populate with daysArr 
	    cbDay = new ComboBox();//Day comboBox
	    cbDay.getItems().addAll(FXCollections.observableArrayList(dayArr));
	    cbDay.setValue("Day");//default string that shows 
	    
	    //create year ComboBox and populate with yearArr 
	    cbYear = new ComboBox();// YEAR comboBox
	    cbYear.getItems().addAll(FXCollections.observableArrayList(yearArr));
	    cbYear.setValue("Year");//default string that shows 

	    //create year ComboBox and populate with Docotors
	    cbDoctors = new ComboBox();
	    ArrayList<String> docs = methods.getDoctors();
	    cbDoctors.getItems().addAll(FXCollections.observableArrayList(docs));
	    cbDoctors.setValue("Pick Doctor");
	   
	    
	    FlowPane textFields = new FlowPane();
	    textFields.setAlignment(Pos.CENTER_LEFT);
	    textFields.setHgap(5.0);
	    textFields.setVgap(5.0);
	    textFields.setPadding(new Insets(5,5,5,5));
	    textFields.getChildren().addAll(firstName, lastName,cbMonth, cbDay,cbYear
	    		,phoneNumber,email, address, insurance, pharmacy,cbDoctors, pass);
	    
	    // SAVE BUTTON
	    registerBt = new Button("Register");
	    registerBt.setStyle("-fx-font-size:12;");
	   // registerBt.setOnAction(new ButtonHandler());
	    registerBt.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	    		{
	    			int month = Integer.parseInt((String) cbMonth.getValue());
	    			int day = Integer.parseInt((String) cbDay.getValue());
	    			int year = Integer.parseInt((String) cbYear.getValue());
	    			//String first, String last, String pass, int day, int month, int year, String address, String insurance, String email,
	        	    //	String pharmacy, String phone, String docFirst, String docLast
	    			String docString = (String) cbDoctors.getValue();
	    			String[]  docNames = docString.split(" ");
	    			//String docFName = docNames[0];
	    			//String docLName = docNames[1];
	    			String docID = docNames[2];
	    			
	    			
					try {
						methods.createPatient(firstName.getText(), lastName.getText(), pass.getText(), day, month, 
											year,  address.getText(), insurance.getText(),
											email.getText(), pharmacy.getText(), phoneNumber.getText(), docID);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
	    			window.setScene(sceneLogin);
	    			window.show();
	    		}
	            
	        }
	    });
	    
	    
	    //PLACE COMPONENTS IN ROOTPANE
	    newAcctRoot.setTop(title);
	    newAcctRoot.setAlignment(title, Pos.CENTER);
	    newAcctRoot.setCenter(textFields);
	    newAcctRoot.setAlignment(textFields, Pos.CENTER);
	    newAcctRoot.setBottom(registerBt);
	    newAcctRoot.setAlignment(registerBt, Pos.CENTER);
	    // Create a scene and place it in the stage
	    sceneNewAcct = new Scene(newAcctRoot, 350, 250);
	    window = primaryStage;
	
	    
		//Create a border pane as the root
	    showWindow();
	    
	   // btnNewAcct.setOnAction(new ButtonHandler());
	    btnNewAcct.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) {
	        	window.setScene(sceneNewAcct);
    			window.show(); 
	            
	        }
	    });
	    btnLogin.setOnAction(new EventHandler<ActionEvent>() {
	        @Override
	        public void handle(ActionEvent event) 
	        {
	    		{
	    			if (methods.authenticate(Login.getText(), Password.getText())) {
	    				
	    				System.out.println("OK!");
	    	
	    				//
	    				//methods.retrieveSingleColumn("Users", "ID", "First", firstName.getText());
	    				//public String retrieveSingleColumn(String table, String column, String primKey,String primKeyValue)
	    				String usertype = methods.retrieveSingleColumn("Users", "UserType", "ID", Login.getText());
	    				if(usertype.equals("Patient")) {
	    					window.close();
	    					PatientView.display(Login.getText());
	    				}
	    				
	    				else if(usertype.equals("Doctor"))
	    				{
	    					
	    					System.out.print("Im a Docotor");
	    					window.close();
	    					DoctorPortal.display(Login.getText());
	    					
	    				}
	    				else 
	    				{
	    					System.out.print("Im a Nurse");
	    					window.close();
	    					NursePortal.display(Login.getText());
	    				}
	     			
	    			}

					else{
					System.out.println("NOT OK!");
				}
	    	}
	        }
	       });
	    
	    userTypeGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
	    		{
	    		public void changed(ObservableValue<? extends Toggle> ob,Toggle o, Toggle n) {
	    			RadioButton tempRB = (RadioButton)userTypeGroup.getSelectedToggle();
	    			if (tempRB != null) {
	    				String selectedUserType = tempRB.getText();
	    				if (selectedUserType == "Doctor") {
	    					//Setting the pharmacy and insurance NOT to be editable.
	    					pharmacy.setText("");
	    					insurance.setText("");
	    					pharmacy.setEditable(false);
	    					insurance.setEditable(false);
	    					
	    					//Removing the doctors list.
	    					textFields.getChildren().remove(cbDoctors);
	    					
	    					//Add the expertise textfield
	    					textFields.getChildren().add(expertise);

	    				}
	    			}
	    			
	    			  
	    		}
	    	
	    }
	
	    		);
	    
	    
		
	}
	public void showWindow() {

	    window.setTitle("User Login"); // Set the stage title
	    window.setScene(sceneLogin); // Login in will always be the first window to any user
	    window.show(); // Display the stage
	}
	
}
