package org.example.javafxdemo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SquareCalculator extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Square Calculator");

        // Create and configure labels
        Label inputLabel = new Label("Enter any number:");
        Label resultLabel = new Label("Square of Enter number:");

        // Create and configure text fields
        TextField inputField = new TextField();
        TextField resultField = new TextField();
        resultField.setEditable(false);

        // Create and configure button
        Button calculateButton = new Button("Calculate Square");
        calculateButton.setOnAction(event -> {
            try {
                double number = Double.parseDouble(inputField.getText());
                double square = number * number;
                resultField.setText(String.valueOf(square));
            } catch (NumberFormatException e) {
                resultField.setText("Invalid input");
            }
        });

        // Create and configure layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(inputLabel, 0, 0);
        gridPane.add(inputField, 1, 0);
        gridPane.add(calculateButton, 1, 1);
        gridPane.add(resultLabel, 0, 2);
        gridPane.add(resultField, 1, 2);

        // Create and configure scene
        Scene scene = new Scene(gridPane, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
