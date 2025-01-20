package org.example.javafxdemo;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class RectangleExam extends Application {

    @Override
    public void start(Stage stage) {

        // Set title for the stage
        stage.setTitle("Creating Rectangle");

        // Create a rectangle
        Rectangle rectangle = new Rectangle(50.0, 50.0, 200.0, 100.0);

        // Create a Group
        Group group = new Group(rectangle);

        // Translate the rectangle to a position
        rectangle.setTranslateX(100);
        rectangle.setTranslateY(100);

        // Create a scene
        Scene scene = new Scene(group, 500, 300);

        // Set the scene
        stage.setScene(scene);

        // Show the stage
        stage.show();
    }

    // Main Method
    public static void main(String[] args) {
        // Launch the application
        launch(args);
    }
}
