
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class TrafficLight extends Application {

	public TrafficLight() {

	} // end of constructor LayoutsDemo()

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane gridPane = new GridPane();
		gridPane.setVgap(20.0);
		gridPane.setHgap(20.0);
		gridPane.add(new Button("Red"), 1, 1);
		gridPane.add(new Button("Yellow"), 1, 2);
		gridPane.add(new Button("Green"), 1, 3);

		Circle red = new Circle(20);
		Circle yellow = new Circle(20);
		Circle green = new Circle(20);

		red.setFill(Color.RED);
		yellow.setFill(Color.YELLOW);
		green.setFill(Color.GREEN);

		gridPane.add(red, 2, 1);
		gridPane.add(yellow, 2, 2);
		gridPane.add(green, 2, 3);

		gridPane.setStyle("-fx-background-color: black");

		
		// Set the scene and the stage
		Scene scene = new Scene(gridPane, 350, 275);
		primaryStage.setScene(scene);
		primaryStage.setTitle("JavaFX Layouts Demo");

		// Display the GUI
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

} // end of class LayoutDemo