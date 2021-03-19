import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * Class for demonstrating JavaFX GUIs, shapes, and event handling.
 * 
 * @author Sean Holden (holdens@my.erau.edu) <br>
 *         October 2014
 */
public class EventsDemo extends Application {

	// Pane
	private BorderPane borderPane;

	// Menu stuff
	private MenuBar menuBar; // MenuBar
	private Menu menuFile, menuShapes, menuHelp; // Menus
	private MenuItem miClose; // Close MenuItem
	/** Menu item for showing/hiding a shape */
	private CheckMenuItem miCircle, miLine, miTriangle; // CheckMenuItems for each shape
	private MenuItem miShowAll, miClearAll; // Shows/clears all shapes
	private MenuItem miAbout; // Displays info about the program

	// Shapes
	/** Contains the shapes */
	private Pane shapePane;
	private Circle circle;
	/** The line that goes through the circle */
	private Line line;
	/** One of the lines that form the triangle in which the circle is inscribed */
	private Line triLineLeft, triLineRight, triLineBottom;

	/** Default constructor */
	public EventsDemo() {

		// Create the BorderPane
		borderPane = new BorderPane();
		shapePane = new Pane();

		/* MENU CREATION */
		// Create MenuItems
		miClose = new MenuItem("Close");
		miCircle = new CheckMenuItem("Circle");
		miLine = new CheckMenuItem("Line");
		miTriangle = new CheckMenuItem("Triangle");
		miShowAll = new MenuItem("Show all");
		miClearAll = new MenuItem("Clear all");
		miAbout = new MenuItem("About...");
		// Create Menus
		menuFile = new Menu("File");
		menuShapes = new Menu("Shapes");
		menuHelp = new Menu("Help");
		// Create MenuBar
		menuBar = new MenuBar();
		// Add menu items to respective menus
		menuFile.getItems().add(miClose);
		menuShapes.getItems().addAll(miCircle, miLine, miTriangle, new SeparatorMenuItem(), miShowAll, miClearAll);
		menuHelp.getItems().add(miAbout);
		// Add menus to menuBar
		menuBar.getMenus().addAll(menuFile, menuShapes, menuHelp);

		/* SHAPE CREATION */
		// Circle
		circle = new Circle(100);
		circle.centerXProperty().bind(shapePane.widthProperty().divide(2));
		circle.centerYProperty().bind(shapePane.heightProperty().divide(1.5));
		circle.setFill(null);
		circle.setStroke(Color.BLACK);
		// Center line
		line = new Line();
		line.setStroke(Color.BLACK);
		line.startXProperty().bind(circle.centerXProperty());
		line.endXProperty().bind(circle.centerXProperty());
		line.startYProperty().bind(circle.centerYProperty().subtract(2 * circle.getRadius()));
		line.endYProperty().bind(circle.centerYProperty().add(circle.getRadius()));
		// Triangle
		triLineLeft = new Line();
		triLineRight = new Line();
		triLineBottom = new Line();
		triLineLeft.setStroke(Color.BLACK);
		triLineRight.setStroke(Color.BLACK);
		triLineBottom.setStroke(Color.BLACK);
		// Bindings - left line
		triLineLeft.startXProperty().bind(line.startXProperty());
		triLineLeft.startYProperty().bind(line.startYProperty());
		triLineLeft.endXProperty().bind(circle.centerXProperty().subtract(1.75 * circle.getRadius()));
		triLineLeft.endYProperty().bind(line.endYProperty());
		// Bindings - right line
		triLineRight.startXProperty().bind(line.startXProperty());
		triLineRight.startYProperty().bind(line.startYProperty());
		triLineRight.endXProperty().bind(circle.centerXProperty().add(1.75 * circle.getRadius()));
		triLineRight.endYProperty().bind(line.endYProperty());
		// Bindings - bottom line
		triLineBottom.startXProperty().bind(triLineLeft.endXProperty());
		triLineBottom.startYProperty().bind(triLineLeft.endYProperty());
		triLineBottom.endXProperty().bind(triLineRight.endXProperty());
		triLineBottom.endYProperty().bind(triLineRight.endYProperty());

//		shapeGroup = new Group(circle, line, triangle);
		shapePane.getChildren().addAll(circle, line, triLineLeft, triLineRight, triLineBottom);
		shapePane.setStyle("-fx-background-color: beige;");

		circle.setVisible(false);
		line.setVisible(false);
		triLineBottom.setVisible(false);
		triLineLeft.setVisible(false);
		triLineRight.setVisible(false);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		/* TODO Add the event handlers here */

		miCircle.setOnAction(new CircleHandler());
		miLine.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				line.setVisible(miLine.isSelected());
			}
		});

		miTriangle.setOnAction((ActionEvent event) -> {
			triLineLeft.setVisible(miTriangle.isSelected());
			triLineRight.setVisible(miTriangle.isSelected());
			triLineBottom.setVisible(miTriangle.isSelected());
		});
		
		miShowAll.setOnAction(new ShowAllHandler(true));
		miClearAll.setOnAction(new ShowAllHandler(false));
		
		miAbout.setOnAction((ActionEvent event) -> {
			showAbout();
		});
		
		miClose.setOnAction((ActionEvent event) -> {
			Platform.exit();
		});
		
		// Put everything together:
		Scene scene = new Scene(borderPane, 450, 400);
		// Add the menubar and shapes to the borderpane
		borderPane.setTop(menuBar);
		borderPane.setCenter(shapePane);
		// Configure and display the stage
		primaryStage.setScene(scene);
		primaryStage.setTitle("JavaFX Fun Stuff");
		primaryStage.show();
	}

	public static void main(String[] args) {
		EventsDemo.launch(args);
	}

	/** Shows information about the program in it's own window */
	private void showAbout() {

		final String aboutText = "This program was written by Sean Holden for use in the CS225 lab sections at Embry-Riddle Aeronautical University.  "
				+ "Modification and redistribution is highly encouraged.  No rights reserved.  Void where prohibited.  Batteries not included.";

		Alert popup = new Alert(Alert.AlertType.INFORMATION, aboutText, ButtonType.OK);

		popup.setHeaderText("About This Program");
		popup.setTitle("About");
		popup.showAndWait();

	}

	/**
	 * Changes the visibility of the circle based on the status of the circle menu
	 * item
	 */
	private class CircleHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			circle.setVisible(miCircle.isSelected());
		}
	}

	/** Checks all of the shape menu items to show all of the shapes */
	private class ShowAllHandler implements EventHandler<ActionEvent> {
		
		private boolean show;

		// TODO Add class variable and constructor here...
		public ShowAllHandler(boolean show) {
			this.show = show;
		}

		@Override
		public void handle(ActionEvent e) {
			circle.setVisible(show);
			line.setVisible(show);
			triLineBottom.setVisible(show);
			triLineLeft.setVisible(show);
			triLineRight.setVisible(show);

			miTriangle.setSelected(show);
			miCircle.setSelected(show);
			miLine.setSelected(show);
		}
	}

}
