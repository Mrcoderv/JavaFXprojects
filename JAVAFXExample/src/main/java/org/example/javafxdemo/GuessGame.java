package org.example.javafxdemo;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuessGame extends Application {

    private int myNumber;
    private int attempts;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        myNumber = (int) (Math.random() * 100) + 1;
        attempts = 0;

        // UI Components
        Label instructionLabel = new Label("Guess a number between 1 and 100:");
        TextField inputField = new TextField();
        Button guessButton = new Button("Guess");
        Button resetButton = new Button("Reset Game");
        Label feedbackLabel = new Label();
        Label attemptsLabel = new Label("Attempts: 0");

        // Layout
        VBox layout = new VBox(10, instructionLabel, inputField, guessButton, resetButton, feedbackLabel, attemptsLabel);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // Button Action Handlers
        guessButton.setOnAction(e -> {
            String input = inputField.getText();
            try {
                int guessedNumber = Integer.parseInt(input);
                attempts++;
                attemptsLabel.setText("Attempts: " + attempts);

                if (guessedNumber == myNumber) {
                    feedbackLabel.setText("WOOHOO! Correct! You guessed it in " + attempts + " attempts.");
                    feedbackLabel.setStyle("-fx-text-fill: green;");
                } else if (guessedNumber < myNumber) {
                    feedbackLabel.setText("Too low! Try a higher number.");
                    feedbackLabel.setStyle("-fx-text-fill: orange;");
                } else {
                    feedbackLabel.setText("Too high! Try a lower number.");
                    feedbackLabel.setStyle("-fx-text-fill: orange;");
                }

            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Please enter a valid number.");
                feedbackLabel.setStyle("-fx-text-fill: red;");
            }
            inputField.clear();
        });

        resetButton.setOnAction(e -> {
            myNumber = (int) (Math.random() * 100) + 1;
            attempts = 0;
            feedbackLabel.setText("");
            attemptsLabel.setText("Attempts: 0");
            inputField.clear();
        });

        // Setting the Scene
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setTitle("Guess My Number Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
