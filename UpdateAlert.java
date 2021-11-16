import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;



public class UpdateAlert {
	public static void display() {
		Stage window =  new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Updated");
		
		Label label = new Label("Your information has been updated!");
		Button closeButton = new Button ("Close");
		closeButton.setOnAction(e -> window.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}
}
