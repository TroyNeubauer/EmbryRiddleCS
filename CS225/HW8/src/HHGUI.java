import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.stage.FileChooserBuilder;
import javafx.stage.FileChooser;

import java.lang.RuntimeException;
import java.io.*;

public class HHGUI extends Application {

	private Pane pane;
	private Manager mgr;
	private Circle hareCircle, houndCircle;
	private double time = 0;
	private Rectangle ground[];
	private Label winnerLabel;
	private boolean isBurningFuel = false;

	private Button start, randomHeights, loadHeights, saveHeights, burnFuel;

	private final double SCALE = 0.4, HEIGHT = SCALE * 2500, WIDTH = SCALE * 2000;

	public HHGUI() {

		pane = new Pane();
		pane.setStyle("-fx-background-color: lightblue;");
		mgr = new Manager();
		resetHeights();

		hareCircle = new Circle(SCALE * mgr.getHare().getX(), (SCALE * mgr.getHare().getY()), 10, Color.PURPLE);
		houndCircle = new Circle(SCALE * mgr.getHound().getX(), (SCALE * mgr.getHound().getY()), 10, Color.HOTPINK);

		pane.getChildren().addAll(hareCircle, houndCircle);

		this.start = new Button("Start");
		this.start.setLayoutX(0);

		this.randomHeights = new Button("Use Random Heights");
		this.randomHeights.setLayoutX(200);

		this.loadHeights = new Button("Load Heights File");
		this.loadHeights.setLayoutX(400);

		this.saveHeights = new Button("Save Heights File");
		this.saveHeights.setLayoutX(600);
		
		this.burnFuel = new Button("Burn Fuel");
		this.burnFuel.setLayoutX(800);

		pane.getChildren().addAll(start, randomHeights, loadHeights, saveHeights, burnFuel);

		Timeline timeline = new Timeline();


		timeline.setCycleCount(Timeline.INDEFINITE);

		KeyFrame keyframe = new KeyFrame(Duration.millis(10), action -> {

			if (!mgr.gameOver(mgr.getHare())) {
				mgr.getHare().move(time);
				hareCircle.setCenterX(SCALE * mgr.getHare().getX());
				hareCircle.setCenterY(HEIGHT - SCALE * mgr.getHare().getY());
				if (time % 1.5 == 0) {
					mgr.getHare().boost();
				}
			} else {
				if (!mgr.gameOver(mgr.getHound())) {
					mgr.getHound().move(time);
					houndCircle.setCenterX(SCALE * mgr.getHound().getX());
					houndCircle.setCenterY(HEIGHT - SCALE * mgr.getHound().getY());
					if (this.isBurningFuel) {
						mgr.getHound().burnFuel();
					}

				}
			}

			if (mgr.gameOver(mgr.getHare()) && mgr.gameOver(mgr.getHound()))

			{
				timeline.stop();
				// System.out.println("Done");
				winnerLabel = new Label(mgr.distanceBetween(mgr.getHare(), mgr.getHound()) + "\n"
						+ mgr.winner(mgr.getHare(), mgr.getHound()));
				winnerLabel.setLayoutX(100);
				winnerLabel.setLayoutY(100);
				pane.getChildren().add(winnerLabel);
			}

			time += 0.1;

		});

		this.start.setOnAction((event) -> {
			timeline.getKeyFrames().add(keyframe);
			timeline.play();
		});

		this.randomHeights.setOnAction((event) -> {
			this.mgr.genPlateaus();
			resetHeights();
		});

		this.loadHeights.setOnAction((event) -> {
			FileChooser builder = FileChooserBuilder.create().title("Load heights file").build();
			File file = builder.showOpenDialog(null);
			System.out.println("file is: " + file);

			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));

				for (int i = 0; i < mgr.getPlat().length; i++) {
					String line = reader.readLine();
					if (line == null) {
						throw new RuntimeException("Could not read height #" + i + " from file " + file
								+ ". File truncated or corrputed??!!");
					}
					try {
						double value = Double.parseDouble(line);
						mgr.getPlat()[i] = value;
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
				reader.close();

			} catch (Exception e) {
			}
			resetHeights();
		});

		this.saveHeights.setOnAction((event) -> {
			FileChooser builder = FileChooserBuilder.create().title("Save heights file").build();
			File file = builder.showSaveDialog(null);
			System.out.println("file is: " + file);

			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));

				for (double height : mgr.getPlat()) {
					writer.write(String.valueOf(height));
					writer.write('\n');
				}
				writer.close();

			} catch (Exception e) {

			}

		});

		this.burnFuel.setOnAction((event) -> {
			if (this.isBurningFuel)  {
				this.burnFuel.setText("Burn Fuel");
			} else {
				this.burnFuel.setText("Stop Burning Fuel");
			}
			this.isBurningFuel = !this.isBurningFuel;
		});

	}

	private void resetHeights() {
		if (ground != null) {
			for (Rectangle rec : ground) {
				pane.getChildren().remove(rec);
			}
		}
		ground = new Rectangle[mgr.getPlat().length];

		for (int i = 0; i < mgr.getPlat().length; i++) {
			ground[i] = new Rectangle(SCALE * 100, SCALE * mgr.getPlat()[i], Color.BLACK);
			ground[i].setLayoutX(SCALE * 100 * i);
			ground[i].setLayoutY(HEIGHT - SCALE * mgr.getPlat()[i]);
			pane.getChildren().add(ground[i]);
		}
	}

	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(pane, WIDTH, HEIGHT);
		stage.setScene(scene);
		stage.setTitle("Hound & Hare");
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
