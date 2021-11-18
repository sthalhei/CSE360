package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class Main extends Application {
	
	private final int width = 800;
	private final int height = 600;
	
	//1.	Instantiating the elements
	Label appointmentLabel = new Label ("Appointment for John Smith");
	Label patientMRN = new Label ("Patient MRN: 750312");
	Label findingsLabel = new Label ("Findings");
	Label perscriptionLabel = new Label ("Perscription");
	Label recommendationLabel = new Label("Recommendations");
	TextArea findings = new TextArea();
	TextArea recommendations = new TextArea();
	TextArea perscription = new TextArea();
	Button submitButton = new Button("Submit");
	Button backButton = new Button("<-- Back");
	Button saveButton = new Button ("Save and back");
	
	public void start(Stage primaryStage) {
		try {
			
			
			
			findings.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. "
					+ "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an "
					+ "unknown printer took a galley of type and scrambled itto make a type specimen book. "
					+ "It has survived not only five centuries, but also the leap into electronic typesetting, "
					+ "remaining essentially unchanged. It was popularised in the 1960s with the release of "
					+ "Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing "
					+ "software like Aldus PageMaker including versions of Lorem Ipsum.\n"
					+ "\n"
					+ "");
			
			findings.setWrapText(true);
			findings.setMaxWidth(350);
			
			
			recommendations.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. "
					+ "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an "
					+ "unknown printer took a galley of type and scrambled itto make a type specimen book. "
					+ "It has survived not only five centuries, but also the leap into electronic typesetting, "
					+ "remaining essentially unchanged. It was popularised in the 1960s with the release of "
					+ "Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing "
					+ "software like Aldus PageMaker including versions of Lorem Ipsum.\n"
					+ "\n"
					+ "");
			recommendations.setWrapText(true);
			recommendations.setMaxWidth(350);
			
			
			perscription.setText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. "
					+ "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an "
					+ "unknown printer took a galley of type and scrambled itto make a type specimen book. "
					+ "It has survived not only five centuries, but also the leap into electronic typesetting, "
					+ "remaining essentially unchanged. It was popularised in the 1960s with the release of "
					+ "Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing "
					+ "software like Aldus PageMaker including versions of Lorem Ipsum.\n"
					+ "\n"
					+ "");
			perscription.setWrapText(true);
			perscription.setMaxWidth(350);
			
				
			
			
			
			//Styling
			appointmentLabel.setFont(Font.font(24));
			patientMRN.setFont(Font.font(18));
			
			//2.	Instantiating the nodes
			
			
			//2.1	The header
			HBox header = new HBox();
			//Creating a middle part in the header
			
			header.setSpacing(20);
			header.getChildren().addAll(backButton, appointmentLabel, patientMRN);
			
			
			//2.2	The rest of the interface
			HBox details = new HBox();
			details.setMinHeight(500);
			
			//The details left box
			GridPane detailsLeft = new GridPane();
			detailsLeft.add(findingsLabel, 0, 0);
			detailsLeft.add(recommendationLabel, 0, 1);
			detailsLeft.add(findings, 1, 0);
			detailsLeft.add(recommendations, 1, 1);
			detailsLeft.add(saveButton, 1, 2);
			
			detailsLeft.setMargin(findings, new Insets(20,20,20,20));
			detailsLeft.setMargin(recommendations, new Insets(20,20,20,20));
			detailsLeft.setMargin(saveButton, new Insets(20,20,20,20));
			
			//The details right box
			VBox detailsRight = new VBox();
			detailsRight.getChildren().add(perscriptionLabel);
			detailsRight.getChildren().add(perscription);
			detailsRight.getChildren().add(submitButton);
			
			detailsRight.setMargin(perscription, new Insets(20,20,20,20));
			detailsRight.setMargin(submitButton, new Insets(20,20,20,20));
			
			//Adding the left and right pane to the details pane
			details.getChildren().addAll(detailsLeft, detailsRight);
			
			details.setMargin(detailsLeft, new Insets(20,20,20,20));
			details.setMargin(detailsRight, new Insets(20,20,20,20));
			
			
			
			//2.3	The root (MAIN) pane
			VBox root = new VBox();
			
			//Setting up margins for the main pane
            root.setMargin(header, new Insets(20, 20, 20, 20));  
            root.setMargin(details, new Insets(20, 20, 20, 20)); 
            
			root.getChildren().add(header);
			root.getChildren().add(details);
			
			Scene scene = new Scene(root,width,height);
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
						
				else if (source == submitButton)
				{
					System.out.print("Submit\n");
				}
				
				else if (source == saveButton)
				{
					System.out.print("Save\n");
				}
			}
		}
				
				
		//Registering the event handler
		backButton.setOnAction(new ButtonHandler());
		submitButton.setOnAction(new ButtonHandler());
		saveButton.setOnAction(new ButtonHandler());
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
