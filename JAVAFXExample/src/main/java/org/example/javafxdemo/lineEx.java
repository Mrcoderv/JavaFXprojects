package org.example.javafxdemo;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class lineEx extends Application {
    @Override
    public void start(Stage stage) {

        stage.setTitle("Creating Line By JavaFX");


        Line line = new Line(100, 100, 200, 200);


        Group group = new Group(line);

        Scene scene = new Scene(group, 500, 300);

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
