module org.example.fieklibrary {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.xml.crypto;


    opens org.example.fieklibrary to javafx.fxml;
    exports org.example.fieklibrary;
    exports app;
    opens controller to javafx.fxml;
}