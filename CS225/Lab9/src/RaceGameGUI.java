
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RaceGameGUI extends Application {
    Pane pane = new Pane();

	public RaceGameGUI() {
		RaceGame rg = new RaceGame();

		// start button

		Button start = new Button();
		start.relocate(100, 270);
		start.setText("Start");
		start.autosize();



		// shapes
		Circle racer1 = new Circle(15);
		racer1.setFill(Color.RED);

		racer1.relocate(0, 50);
		Circle racer2 = new Circle(15);
		racer2.setFill(Color.BLUE);
		racer2.relocate(0, 100);
		Circle racer3 = new Circle(15);
		racer3.setFill(Color.GREEN);
		racer3.relocate(0, 150);
		Circle racer4 = new Circle(15);
		racer4.setFill(Color.YELLOW);
		racer4.relocate(0, 200);



		// text names
		Text t1 = new Text(10, 10, "1");
		t1.relocate(racer1.getCenterX() + 13, 60);
		t1.setStroke(Color.WHITE);
		Text t2 = new Text(10, 10, "2");
		t2.relocate(racer2.getCenterX() + 13, 110);
		t2.setStroke(Color.WHITE);
		Text t3 = new Text(10, 10, "3");
		t3.relocate(racer3.getCenterX() + 13, 160);
		t3.setStroke(Color.WHITE);
		Text t4 = new Text(10, 10, "4");
		t4.relocate(racer4.getCenterX() + 13, 210);
		t4.setStroke(Color.BLACK);

		// Winner

		// lines
		Line Finish = new Line(215, 45, 215, 245);
		Line Lane1 = new Line(0, 45, 250, 45);
		Line Lane2 = new Line(0, 95, 250, 95);
		Line Lane3 = new Line(0, 145, 250, 145);
		Line Lane4 = new Line(0, 195, 250, 195);
		Line Lane5 = new Line(0, 245, 250, 245);
		Lane1.setStroke(Color.WHITE);
		Lane2.setStroke(Color.WHITE);
		Lane3.setStroke(Color.WHITE);
		Lane4.setStroke(Color.WHITE);
		Lane5.setStroke(Color.WHITE);

		// Background
		Rectangle Grass = new Rectangle(250, 300);
		Grass.setFill(Color.DARKGREEN);
		Grass.relocate(0, 0);
		Rectangle Street = new Rectangle(250, 200);
		Street.relocate(0, 45);
		Street.setFill(Color.GREY);

		// what a PANE
		pane.getChildren().add(Grass);
		pane.getChildren().add(Street);
		pane.getChildren().add(Finish);
		pane.getChildren().add(Lane1);
		pane.getChildren().add(Lane2);
		pane.getChildren().add(Lane3);
		pane.getChildren().add(Lane4);
		pane.getChildren().add(Lane5);
		pane.getChildren().add(start);
		pane.getChildren().add(racer1);
		pane.getChildren().add(racer2);
		pane.getChildren().add(racer3);
		pane.getChildren().add(racer4);
		pane.getChildren().add(t1);
		pane.getChildren().add(t2);
		pane.getChildren().add(t3);
		pane.getChildren().add(t4);

		// we do not have while loops in GUI so a Timeline is used
		// make timeline
		Timeline timeline = new Timeline();
		// make timeline repeat infinite number of times
		timeline.setCycleCount(Timeline.INDEFINITE);
		// timeline uses keyframes so make a keyframe
		// make a keyframe that runs every half second
		// keyframe can use a lambda expression for its action
		KeyFrame keyframe = new KeyFrame(Duration.millis(500), action -> {

			// do something
			// System.out.println("HI");
			rg.moveRacers();
			double racer1Location = rg.getRacers()[0].getLocation();
			double racer2Location = rg.getRacers()[1].getLocation();
			double racer3Location = rg.getRacers()[2].getLocation();
			double racer4Location = rg.getRacers()[3].getLocation();

			racer1.setLayoutX(2 * racer1Location);
			t1.setLayoutX((2 * racer1Location) - 13);
			racer2.setLayoutX(2 * racer2Location);
			t2.setLayoutX((2 * racer2Location) - 13);
			racer3.setLayoutX(2 * racer3Location);
			t3.setLayoutX((2 * racer3Location) - 13);
			racer4.setLayoutX(2 * racer4Location);
			t4.setLayoutX((2 * racer4Location) - 13);

			if (rg.gameOver() == true) {
                String winner = rg.getWinner();
				System.out.println(winner);
				Text TheWinner = new Text(100, 100, winner);
				TheWinner.setStyle("-fx-font: 25 arial;");
				TheWinner.setStroke(Color.GOLD);
				TheWinner.setLayoutX(-100);
				TheWinner.setLayoutY(50);
				pane.getChildren().add(TheWinner);
				timeline.pause();
			}
		});



		// add keyframe to timeline
		timeline.getKeyFrames().add(keyframe);

		// start the timeline
		start.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				timeline.play();
			}

		});
	}

	@Override
	public void start(Stage stage) {

 		
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
