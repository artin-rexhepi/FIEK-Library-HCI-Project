package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dto.IssuedBookDto;
import service.IssuedBooksService;
import app.Navigator;

import java.sql.Timestamp;

public class HuazoLibrinController {

    @FXML
    private TextField txtIDStudentore;

    @FXML
    private TextField txtIDLibrit;

    private IssuedBooksService issuedBooksService;

    public HuazoLibrinController() {
        // Instantiate the IssuedBooksService
        this.issuedBooksService = new IssuedBooksService();
    }

    @FXML
    private void handleRuaj() {
        String memberId = txtIDStudentore.getText();
        String isbn = txtIDLibrit.getText();
        Timestamp issueTime = new Timestamp(System.currentTimeMillis());
        int renewCount = 14; // Set initial renew count to 14 days

        // Create an IssuedBookDto object with the retrieved data
        IssuedBookDto issuedBookDto = new IssuedBookDto(isbn, memberId, issueTime, renewCount);

        // Call the service method to save the issued book
        boolean isSaved = issuedBooksService.issueBook(issuedBookDto);

        if (isSaved) {
            // Issued book saved successfully, display a success message
            showAlert(Alert.AlertType.INFORMATION, "Success", "Book issued successfully!");
            clearFields();
        } else {
            // Saving issued book failed, display an error message
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to issue book");
        }
    }

    @FXML
    private void handleFshij() {
        clearFields();
    }

    private void clearFields() {
        txtIDStudentore.clear();
        txtIDLibrit.clear();
    }

    @FXML
    public void handleReturn() {
        // Get the current stage from any of the text fields
        Stage stage = (Stage) txtIDStudentore.getScene().getWindow();
        // Navigate to the MANAGE_USERS page
        Navigator.navigate(stage, Navigator.MANAGE_USERS);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
        // Shtimi i event handler për kalimin me Enter neper textfielda
        txtIDStudentore.setOnAction(event -> txtIDLibrit.requestFocus());
        txtIDLibrit.setOnAction(event -> {
            handleRuaj();
            event.consume(); // Për të parandaluar që shtypja e Enter të shkaktojë një ngjarje të tjera si kalimi në fushën tjetër
        });
    }
}
