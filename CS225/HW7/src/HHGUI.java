
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

public class HHGUI extends Application {
	Pane pane = new Pane();

	public static double mapX(double pos) {
		return pos / 3;
	}

	public static double mapY(double pos) {
		return (2500 - pos) / 3;
	}

	public HHGUI() {
		Manager.init();
		Button start = new Button();
		start.relocate(100, 270);
		start.setText("Start");
		start.autosize();

		pane.getChildren().add(start);

		// shapes
		Circle hare = new Circle(15);
		hare.setFill(Color.MAGENTA);

		Circle hound = new Circle(15);
		hare.setFill(Color.PINK);

		pane.getChildren().add(hare);
		pane.getChildren().add(hound);

		for (double x = 0; x < 2000; x += 100) {
			double y = 100;
			while (y < Manager.getHeight(x)) {

				Rectangle rec = new Rectangle(mapX(x), mapY(y), 100, 100);
				rec.setFill(Color.BLACK);

				pane.getChildren().add(rec);
				y += 100;
			}
		}

		// we do not have while loops in GUI so a Timeline is used
		// make timeline
		Timeline timeline = new Timeline();
		// make timeline repeat infinite number of times
		timeline.setCycleCount(Timeline.INDEFINITE);
		// timeline uses keyframes so make a keyframe
		// make a keyframe that runs every half second
		// keyframe can use a lambda expression for its action
		KeyFrame keyframe = new KeyFrame(Duration.millis(100), action -> {
			while (!Manager.isDone()) {

				// do something
				//
				// System.out.println("HI");
				Manager.oneIteration();

				hare.setX(mapX(Manager.getHare().getX()));

			}
			/*
			 * rg.moveRacers(); double racer1Location = rg.getRacers()[0].getLocation();
			 * double racer2Location = rg.getRacers()[1].getLocation(); double
			 * racer3Location = rg.getRacers()[2].getLocation(); double racer4Location =
			 * rg.getRacers()[3].getLocation();
			 * 
			 * racer1.setLayoutX(2 * racer1Location); t1.setLayoutX((2 * racer1Location) -
			 * 13); racer2.setLayoutX(2 * racer2Location); t2.setLayoutX((2 *
			 * racer2Location) - 13); racer3.setLayoutX(2 * racer3Location);
			 * t3.setLayoutX((2 * racer3Location) - 13); racer4.setLayoutX(2 *
			 * racer4Location); t4.setLayoutX((2 * racer4Location) - 13);
			 * 
			 * if (rg.gameOver() == true) { String winner = rg.getWinner();
			 * System.out.println(winner); Text TheWinner = new Text(100, 100, winner);
			 * TheWinner.setStyle("-fx-font: 25 arial;"); TheWinner.setStroke(Color.GOLD);
			 * TheWinner.setLayoutX(-100); TheWinner.setLayoutY(50);
			 * pane.getChildren().add(TheWinner); timeline.pause(); }
			 * 
			 */
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

		stage.setScene(new Scene(pane, 2000 / 3, 2500 / 3));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
