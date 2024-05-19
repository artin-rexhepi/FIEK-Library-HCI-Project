package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.dto.BookDTO;
import service.BookService;

public class BookDeleteController {

    private BookService bookService;

    public BookDeleteController() {
        // Instantiate the BookService
        this.bookService = new BookService();
    }

    @FXML
    private TextField txtISBN;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextField txtSubject;

    @FXML
    private TextField txtPublisher;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtAuthor;

    @FXML
    private void handleBookDelete() {
        // Retrieve book details from UI components
        String isbn = txtISBN.getText();
        String title = txtTitle.getText();
        String subject = txtSubject.getText();
        String publisher = txtPublisher.getText();
        String quantity = txtQuantity.getText();
        String author = txtAuthor.getText();

        // Perform validation
        if (!validateInput(isbn, title, subject, publisher, quantity, author)) {
            return;
        }

        // Create a BookDTO object with the retrieved data
        BookDTO bookToDelete = new BookDTO(isbn, title, subject, publisher, quantity, author);

        // Call the service method to delete the book
        boolean isDeleted = bookService.deleteBook(bookToDelete);

        if (isDeleted) {
            // Book deletion successful, display a success message
            showAlert(Alert.AlertType.INFORMATION, "Success", "The book has been deleted successfully!");
            clearFields();
        } else {
            // Book deletion failed, display an error message
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete the book");
        }
    }

    @FXML
    private void handleBookDeleteCancel() {
        clearFields();
    }

    private boolean validateInput(String isbn, String title, String subject, String publisher, String quantity, String author) {
        // Perform validation on user input
        if (isbn.isEmpty() || title.isEmpty() || subject.isEmpty() || publisher.isEmpty() || quantity.isEmpty() || author.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all fields");
            return false;
        }

        // Additional validation logic can be added here

        return true;
    }

    private void clearFields() {
        txtISBN.clear();
        txtTitle.clear();
        txtSubject.clear();
        txtPublisher.clear();
        txtQuantity.clear();
        txtAuthor.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
