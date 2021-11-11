
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Orientation;
import javafx.event.ActionEvent;	//**Need to import
import javafx.event.EventHandler;	//**Need to import

public class Login extends Application {

	public void start (Stage primaryStage) {
		
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
	    Button button1 = new Button("Login");
	    Button button2 = new Button("Forgot Password");
	    Button button3 = new Button("Create Account");
	    
	    //Set Text size 
	    button1.setStyle("-fx-font-size:10");
	    button2.setStyle("-fx-font-size:10");
	    button3.setStyle("-fx-font-size:10");

		//To enable the buttons to be resized
	    button1.setMaxSize(100,25);
		button2.setMaxSize(100,25);
		button3.setMaxSize(90,25);
		
		//set the horizontal gap between above 3 buttons 
		southPane.setHgap(10.0);
		southPane.setVgap(10.0);
		southPane.getChildren().addAll(button1,button2,button3);
	    southPane.setPadding(new Insets(8,8,8,8));//padding to the edge of the box
		
		//Place centerPane and southPane inside the rootPane
	    rootPane.setCenter(centerPane);
	    rootPane.setBottom(southPane);
	    
	   /* //Register the button with a ButtonHandler object
	    ButtonHandler aHandler = new ButtonHandler();
	    button2.setOnAction(aHandler);*/

	    
	    // Create a scene and place it in the stage
	    Scene scene = new Scene(rootPane, 300, 150);
	    primaryStage.setTitle("User Login"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	}
	/*//Create a handler class that handle button event.
	//This class should implements the relevant interface
	private class ButtonHandler implements EventHandler<ActionEvent>
	{
	    //Override the abstact method handle()
	    public void handle(ActionEvent e)
	    {
	       Application.launch(ForgotPassword.class, args);
	    }
    }//end of ButtonHandler*/
	public static void main(String[] args)
	{
		launch(args);
	}

}
