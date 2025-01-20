package org.example.javafxdemo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Stack;

public class CalculatorApp extends Application {

    private TextField display = new TextField();
    private ObservableList<String> historyList = FXCollections.observableArrayList();
    private ListView<String> historyView = new ListView<>(historyList);

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();

        addButtons(grid);

        display.setEditable(false);
        display.setPrefHeight(50);
        grid.add(display, 0, 0, 4, 1);

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(grid, historyView);

        Scene scene = new Scene(vbox, 400, 650); // Adjust the height to accommodate the new button
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setTitle("Scientific Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addButtons(GridPane grid) {
        String[] buttonLabels = {
                "AC", "Ans", "%", "÷",
                "7", "8", "9", "×",
                "4", "5", "6", "−",
                "1", "2", "3", "+",
                "0", ".", "√", "=",
                "C" // Add the history clear button
        };

        int row = 1;
        int col = 0;
        for (String label : buttonLabels) {
            Button button = new Button(label);
            button.setPrefSize(100, 50);
            button.setOnAction(e -> handleButtonClick(label));
            button.getStyleClass().add("button");
            grid.add(button, col, row);
            col++;
            if (col == 4) {
                col = 0;
                row++;
            }
        }
    }

    private void handleButtonClick(String label) {
        if (label.equals("=")) {
            try {
                double result = evaluateExpression(display.getText());
                historyList.add(display.getText() + " = " + result);
                display.setText(String.valueOf(result));
            } catch (Exception e) {
                display.setText("Error");
            }
        } else if (label.equals("AC")) {
            display.clear();
        } else if (label.equals("C")) { // Handle the history clear button
            historyList.clear();
        } else if (label.equals("√")) {
            double value = Double.parseDouble(display.getText());
            historyList.add("√" + value + " = " + Math.sqrt(value));
            display.setText(String.valueOf(Math.sqrt(value)));
        } else {
            display.appendText(label);
        }
    }

    private double evaluateExpression(String expression) throws Exception {
        return evaluate(convertToPostfix(expression));
    }

    private String convertToPostfix(String infix) {
        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();
        for (char c : infix.toCharArray()) {
            if (Character.isDigit(c) || c == '.') {
                postfix.append(c);
            } else if (c == '×' || c == '÷' || c == '+' || c == '−') {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    postfix.append(' ').append(stack.pop());
                }
                postfix.append(' ');
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            postfix.append(' ').append(stack.pop());
        }
        return postfix.toString();
    }

    private int precedence(char op) {
        if (op == '×' || op == '÷') return 2;
        if (op == '+' || op == '−') return 1;
        return 0;
    }

    private double evaluate(String postfix) throws Exception {
        Stack<Double> stack = new Stack<>();
        for (String token : postfix.split("\\s")) {
            if (token.isEmpty()) continue;
            if (Character.isDigit(token.charAt(0)) || token.length() > 1) {
                try {
                    stack.push(Double.parseDouble(token));
                } catch (NumberFormatException e) {
                    throw new Exception("Invalid number format");
                }
            } else {
                double b = stack.pop();
                double a = stack.pop();
                switch (token.charAt(0)) {
                    case '+': stack.push(a + b); break;
                    case '−': stack.push(a - b); break;
                    case '×': stack.push(a * b); break;
                    case '÷': stack.push(a / b); break;
                    default: throw new Exception("Unknown operator");
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
