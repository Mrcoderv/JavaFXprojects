package org.example.javafxdemo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class ll extends Application {
    @Override
    public void start(Stage primaryStage){
        // create a pane to hold the line
        Pane pane = new Pane();

        // create a line from (50, 50) to (200, 200)
        Line line = new Line();
        line.setStartX(50);
        line.setStartY(50);
        line.setEndX(200);
        line.setEndY(200);

        // create a scene and set it to the stage
        Scene scene = new Scene(pane, 300, 300);

        primaryStage.setTitle("Drawing a line");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);       // launch the application
    }
}