module org.example.javafxExample {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.javafxdemo to javafx.fxml;
    exports org.example.javafxdemo;
}