
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

public class HW9 extends Application {

    private Pane pane;

    private void addPane(double x, double y, double width, double height, boolean horizontal) {
        Pane pane1 = new Pane();
        pane1.setStyle("-fx-border-color: black");
        pane1.relocate(x, y);
        pane1.setPrefSize(width, height);
        pane1.setMinSize(width, height);
        pane1.setMaxSize(width, height);

        Button button = new Button("Split");
        pane1.getChildren().add(button);
        button.setOnAction((event) -> {
            this.pane.getChildren().remove(pane1);
            if (horizontal) {
                addPane(x,               y, width / 2.0, height, !horizontal);
                addPane(x + width / 2.0, y, width / 2.0, height, !horizontal);
            } else {
                addPane(x, y,                width, height / 2.0, !horizontal);
                addPane(x, y + height / 2.0, width, height / 2.0, !horizontal);
            }
        });
        button.relocate(width / 2.0, height / 2.0);

        this.pane.getChildren().add(pane1);
    }

    public HW9() {
        pane = new Pane();
    }

    @Override
    public void start(Stage stage) {
        double width = 1280;
        double height = 720;
        Scene scene = new Scene(pane, width, height);
        addPane(0, 0, width, height, true);

        stage.setScene(scene);
        stage.setTitle("Split me!");
        stage.resizableProperty().set(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
