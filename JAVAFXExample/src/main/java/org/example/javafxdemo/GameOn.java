package org.example.javafxdemo;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameOn extends Application {

    private static final double WIDTH = 600;
    private static final double HEIGHT = 400;
    private static final double PADDLE_WIDTH = 100;
    private static final double PADDLE_HEIGHT = 20;
    private static final double BALL_RADIUS = 15;

    private Rectangle paddle;
    private Circle ball;
    private double ballSpeedY = 3;
    private double ballSpeedX = 2;
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private long startTime;
    private Text scoreText;
    private Text gameOverText;
    private AnimationTimer timer;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        // Create borders
        Line topBorder = new Line(0, 0, WIDTH, 0);
        Line leftBorder = new Line(0, 0, 0, HEIGHT);
        Line rightBorder = new Line(WIDTH, 0, WIDTH, HEIGHT);
        topBorder.setStroke(Color.BLACK);
        leftBorder.setStroke(Color.BLACK);
        rightBorder.setStroke(Color.BLACK);

        paddle = new Rectangle(WIDTH / 2 - PADDLE_WIDTH / 2, HEIGHT - 40, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFill(Color.BLUE);

        ball = new Circle(WIDTH / 2, 50, BALL_RADIUS);
        ball.setFill(Color.RED);
        
        scoreText = new Text(10, 20, "Score: 0");
        scoreText.setFont(Font.font(18));
        scoreText.setFill(Color.BLACK);

        gameOverText = new Text(WIDTH / 2 - 100, HEIGHT / 2, "Game Over!");
        gameOverText.setFont(Font.font(30));
        gameOverText.setFill(Color.RED);
        gameOverText.setVisible(false);

        root.getChildren().addAll(topBorder, leftBorder, rightBorder, paddle, ball, scoreText, gameOverText);

        Scene scene = new Scene(root, WIDTH, HEIGHT);

        // Key event handlers
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                moveLeft = true;
            } else if (event.getCode() == KeyCode.RIGHT) {
                moveRight = true;
            }
        });

        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.LEFT) {
                moveLeft = false;
            } else if (event.getCode() == KeyCode.RIGHT) {
                moveRight = false;
            }
        });

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (startTime == 0) {
                    startTime = now;
                }
                update((now - startTime) / 1_000_000_000.0);
            }
        };

        timer.start();

        primaryStage.setTitle("Catch the Ball Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void update(double elapsedTime) {
        // Move paddle
        if (moveLeft && paddle.getX() > 0) {
            paddle.setX(paddle.getX() - 5);
        }
        if (moveRight && paddle.getX() < WIDTH - PADDLE_WIDTH) {
            paddle.setX(paddle.getX() + 5);
        }

        // Gradually increase ball speed
        double speedMultiplier = 1 + (elapsedTime / 20); // Increases speed over time
        double adjustedBallSpeedY = ballSpeedY * speedMultiplier;
        double adjustedBallSpeedX = ballSpeedX * speedMultiplier;

        // Move ball
        ball.setCenterY(ball.getCenterY() + adjustedBallSpeedY);
        ball.setCenterX(ball.getCenterX() + adjustedBallSpeedX);

        // Ball collision with walls
        if (ball.getCenterX() <= BALL_RADIUS || ball.getCenterX() >= WIDTH - BALL_RADIUS) {
            ballSpeedX *= -1;
        }
        if (ball.getCenterY() <= BALL_RADIUS) {
            ballSpeedY *= -1;
        }

        // Ball collision with paddle
        if (ball.getBoundsInParent().intersects(paddle.getBoundsInParent())) {
            ballSpeedY *= -1;
        }

        // Ball falls off the screen
        if (ball.getCenterY() > HEIGHT) {
            gameOverText.setVisible(true);
            timer.stop();
        } else {
            gameOverText.setVisible(false);
            scoreText.setText(String.format("Score: %.2f", elapsedTime));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
