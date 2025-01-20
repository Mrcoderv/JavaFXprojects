package org.example.javafxdemo;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class LineExample extends Application {

    public void start(Stage stage) {

        stage.setTitle("Creating Line");

        Line line = new Line(10.0f, 10.0f, 200.0f, 140.0f);

        Group group = new Group(line);

        line.setTranslateX(100);
        line.setTranslateY(100);

        Scene scene = new Scene(group, 500, 300);


        stage.setScene(scene);


        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}
