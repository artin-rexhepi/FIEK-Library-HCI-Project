module org.example.fieklibrary {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.fieklibrary to javafx.fxml;
    exports org.example.fieklibrary;
    exports controller;
    opens controller to javafx.fxml;
}