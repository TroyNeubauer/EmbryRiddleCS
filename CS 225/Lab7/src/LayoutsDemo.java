
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * An application used to demonstrate the basics of creating components such as
 * panels, arranging components using layout objects, and nesting components
 * inside each other.
 * 
 * @author Sean Holden, ERAU, October 2014<br>
 *         Based on ExampleLayout.java - a Swing implementation by N. Brixius,
 *         ERAU, March 2000
 *
 */
public class LayoutsDemo extends Application {

	// Declare panes of different layout types
	private FlowPane flowPane;
	private GridPane gridPane;
	private BorderPane borderPane;
	private TilePane tilePane;
	private Pane regularPane;

	private Button
	// Declare buttons for flow pane
	flowBt1, flowBt2, flowBt3, flowBt4, flowBt5, flowBt6,
			// Declare buttons for grid pane
			gridBt1, gridBt2, gridBt3, gridBt4, gridBt5, gridBt6,
			// Declare buttons for border pane
			borderBt1, borderBt2, borderBt3, borderBt4, borderBt5,
			// Declare buttons for tile pane
			tileBt1, tileBt2, tileBt3, tileBt4, tileBt5, tileBt6;

	// Declare text to add to the regular pane
	private Label paneLabel1;

	// Create tabs and the tab pane
	private TabPane tabPane;
	private Tab flowTab, gridTab, borderTab, tileTab, paneTab;

	// constructor
	public LayoutsDemo() {

		// Instantiate the panes
		flowPane = new FlowPane(20, 20);
		gridPane = new GridPane();
		gridPane.setVgap(20.0);
		gridPane.setHgap(20.0);

		borderPane = new BorderPane();
		tilePane = new TilePane();
		regularPane = new Pane();

		// Instantiate buttons for flow pane
		flowBt1 = new Button("Button 1");
		flowBt2 = new Button("Button 2");
		flowBt3 = new Button("Button 3");
		flowBt4 = new Button("Button 4");
		flowBt5 = new Button("Button 5");
		flowBt6 = new Button("Button 6");

		flowBt2.setFont(new Font("Comic Sans MS", 16));
		flowBt3.setFont(Font.font("Comic Sans MS", 16));
		flowBt4.setStyle("-fx-font-family: \"Comic Sans MS\"; -fx-font-size: 16");

		// Instantiate buttons for grid pane
		gridBt1 = new Button("Button 1");
		gridBt2 = new Button("Button 2");
		gridBt3 = new Button("Button 3");
		gridBt4 = new Button("Button 4");
		gridBt5 = new Button("Button 5");
		gridBt6 = new Button("Button 6");

		// Instantiate buttons for border pane
		borderBt1 = new Button("Button 1");
		borderBt2 = new Button("Button 2");
		borderBt3 = new Button("Button 3");
		borderBt4 = new Button("Button 4");
		borderBt5 = new Button("Button 5");

		// Also allow these buttons to take up as much space as possible
		borderBt1.setMaxHeight(Double.MAX_VALUE);
		borderBt1.setMaxWidth(Double.MAX_VALUE);
		borderBt2.setMaxHeight(Double.MAX_VALUE);
		borderBt2.setMaxWidth(Double.MAX_VALUE);
		borderBt3.setMaxHeight(Double.MAX_VALUE);
		borderBt3.setMaxWidth(Double.MAX_VALUE);
		borderBt4.setMaxHeight(Double.MAX_VALUE);
		borderBt4.setMaxWidth(Double.MAX_VALUE);
		borderBt5.setMaxHeight(Double.MAX_VALUE);
		borderBt5.setMaxWidth(Double.MAX_VALUE);

		borderBt5.setRotate(45);

		DropShadow dropShadow = new DropShadow();
		dropShadow.setRadius(5.0);
		dropShadow.setOffsetX(3.0);
		dropShadow.setOffsetY(3.0);
		dropShadow.setColor(Color.color(0.4, 0.5, 0.5));
		borderBt3.setEffect(dropShadow);

		// Instantiate buttons for tile pane
		tileBt1 = new Button("Button 1");
		tileBt2 = new Button("Button 2");
		tileBt3 = new Button("Button 3");
		tileBt4 = new Button("Button 4");
		tileBt5 = new Button("Button 5");
		tileBt6 = new Button("Button 6");

		tileBt4.setStyle("-fx-background-color: red");
		tilePane.setCursor(Cursor.CROSSHAIR);

		// Instantiate label for regular pane
		paneLabel1 = new Label("This layout is best when you want" + "\nto have graphics with specific locations"
				+ "\non the screen.");
		// Set the position
		paneLabel1.setLayoutX(30);
		paneLabel1.setLayoutY(100);
		// Set the appearance
		paneLabel1.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
		paneLabel1.setFont(new Font("Comic Sans MS", 16));

		// Instantiate tabs
		flowTab = new Tab();
		gridTab = new Tab();
		borderTab = new Tab();
		tileTab = new Tab();
		paneTab = new Tab();

		// Instantiate tab pane
		tabPane = new TabPane();

		// Get the list of the children nodes for the FlowPane
		ObservableList<Node> flowPaneChildren = flowPane.getChildren();
		// Add the flow buttons to the list
		flowPaneChildren.add(flowBt1);
		flowPaneChildren.add(flowBt2);
		flowPaneChildren.add(flowBt3);
		flowPaneChildren.add(flowBt4);
		flowPaneChildren.add(flowBt5);
		flowPaneChildren.add(flowBt6);

		// Add the grid buttons to the list
		// Usage: (Node, column, row)
		gridPane.add(gridBt1, 0, 0);
		gridPane.add(gridBt2, 1, 0);
		gridPane.add(gridBt3, 0, 1);
		gridPane.add(gridBt4, 1, 1);
		gridPane.add(gridBt5, 0, 2);
		gridPane.add(gridBt6, 1, 2);

		// Set each part of the BorderPane with a button
		borderPane.setTop(borderBt1);
		borderPane.setBottom(borderBt2);
		borderPane.setLeft(borderBt3);
		borderPane.setRight(borderBt4);
		borderPane.setCenter(borderBt5);

		// Get the list of the children nodes for the StackPane
		ObservableList<Node> tilePaneChildren = tilePane.getChildren();
		// Add the stack buttons to the list
		tilePaneChildren.add(tileBt1);
		tilePaneChildren.add(tileBt2);
		tilePaneChildren.add(tileBt3);
		tilePaneChildren.add(tileBt4);
		tilePaneChildren.add(tileBt5);
		tilePaneChildren.add(tileBt6);

		// Add label to the regular pane
		regularPane.getChildren().add(paneLabel1);

		regularPane.getChildren().add(new Circle(150, 150, 100));
		regularPane.getChildren().add(new Rectangle(150, 150, 100, 50));
		regularPane.getChildren().add(new Line(150, 150, 100, 0));

		// Add the panes to the TabPane
		flowTab.setText("Flow");
		flowTab.setContent(flowPane);
		tabPane.getTabs().add(flowTab);

		gridTab.setText("Grid");
		gridTab.setContent(gridPane);
		tabPane.getTabs().add(gridTab);

		borderTab.setText("Border");
		borderTab.setContent(borderPane);
		tabPane.getTabs().add(borderTab);

		tileTab.setText("Tile");
		tileTab.setContent(tilePane);
		tabPane.getTabs().add(tileTab);

		paneTab.setText("Pane");
		paneTab.setContent(regularPane);
		tabPane.getTabs().add(paneTab);

	} // end of constructor LayoutsDemo()

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Set the scene and the stage
		Scene scene = new Scene(tabPane, 350, 275);
		primaryStage.setScene(scene);
		primaryStage.setTitle("JavaFX Layouts Demo");

		// Display the GUI
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

} // end of class LayoutDemo