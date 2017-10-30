import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class FirstFX extends Application {
	
	Label label1;
	Button b1;
	int i=1;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("T'will be m'first");
		label1 = new Label("Leave Brittany Alone");
		VBox root = new VBox();
		
		Scene scene = new Scene(root, 400, 300);
		stage.setScene(scene);
		
		b1 = new Button("Send nukes");
		b1.setOnAction(new EventHandler<ActionEvent>() {
			// what happens when user clicks button
			public void handle(ActionEvent event) {
				System.out.println("Oy");
				label1.setText("You did it " + i + " times.");
				i--;
			}
		});
		
		root.getChildren().add(label1);
		root.getChildren().add(b1);
		stage.show();
	}
}
