import java.awt.TextArea;
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
public class PatientHistory {
	public static void display(String pFirst, String pLast ,String patientID, String id, String returnPortal) {
		Stage window = new Stage();
	    BorderPane root = new BorderPane();
	    root.setPadding(new Insets(10,10,10,10));
		
	    GridPane pane = new GridPane();
	    pane.setAlignment(Pos.CENTER);
	    pane.setPadding(new Insets(10, 10, 10, 10));
	    pane.setHgap(5);
	    pane.setVgap(10);
	    
	    //Labels Needed 
	    Label allergies = new Label("Allergies");
	    Label healthConcerns = new Label("Health Concerns");
	    Label prescriptions = new Label("Prescriptions");
	    Label summary = new Label ("Summaries");
	    Label header = new Label ("Patient: " + pFirst + ", " + pLast + "  " + patientID);
	    header.setStyle("-fx-font-size:20");
	    //Text Areas
	    TextField tfAllergy = new TextField();
	    TextField tfHealth = new TextField();
	    TextField tfPres = new TextField();
	    TextField tfSums = new TextField();
	    
	    //Retrieve Data for TextFields
	    //getPatientHistory has a string of Date, Allergies, HealthConcerns,Prescriptions, Summary
	    //tfAllergy.setText(value);
	    
	    //Add items to pane 
	    pane.add(allergies, 0, 0);
	    pane.add(healthConcerns, 0, 1);
	    pane.add(prescriptions, 0, 1);
	    pane.add(summary, 0, 3);
	    pane.add(tfAllergy, 1, 0);
	    pane.add(tfHealth, 1, 1);
	    pane.add(tfPres, 1, 2);
	    pane.add(tfSums, 1, 3);
	    
	    Button back = new Button();
	    
	    back.setOnAction(new EventHandler<ActionEvent>() {
	    	public void handle(ActionEvent event) {
	    		Object source = event.getSource();
	    		if (source == back) {
	    			if (returnPortal == "Nurse")
	    			{
	    			window.close();
	    			NursePortal.display(id);
	    			}
	    			if (returnPortal == "Doctor")
	    			{
	    			window.close();
	    			DoctorPortal.display(id);
	    			}
	    		}
	    	}
	    });
	    
	    
	    root.setTop(header);
	    root.setCenter(pane);
	    root.setBottom(back);
	    
	    Scene patientHist = new Scene(root, 400,400);
	    window.setTitle("Patient " + patientID + " History Records");
		window.setScene(patientHist);
		window.show();
	}

}
