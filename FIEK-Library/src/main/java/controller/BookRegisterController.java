package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.dto.BookDTO;
import service.BookService;

public class BookRegisterController {

    private BookService bookService;

    public BookRegisterController() {
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
    private DatePicker datePublicationDate;

    @FXML
    private TextField txtLanguage;

    @FXML
    private TextField txtNumberOfPages;

    @FXML
    private TextField txtAuthorId;

    @FXML
    private void handleBookRegisterSave() {
        // Retrieve book details from UI components
        String ISBN = txtISBN.getText();
        String title = txtTitle.getText();
        String subject = txtSubject.getText();
        String publisher = txtPublisher.getText();
        String language = txtLanguage.getText();
        int numberOfPages = Integer.parseInt(txtNumberOfPages.getText());
        int authorId = Integer.parseInt(txtAuthorId.getText());

        // Create a BookDTO object with the retrieved data
        BookDTO bookDTO = new BookDTO(ISBN, title, subject, publisher, datePublicationDate.getValue(),
                language, numberOfPages, authorId);

        // Call the service method to register the book
        boolean isRegistered = bookService.registerBook(bookDTO);

        if (isRegistered) {
            // Book registration successful, display a success message
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Libri është regjistruar me sukses!");
            clearFields();
        } else {
            // Book registration failed, display an error message
            showAlert(Alert.AlertType.ERROR, "Error", "Dështim në regjistrimin e librit");
        }
    }

    @FXML
    private void handleBookRegisterCancel() {
        clearFields();
    }

    private void clearFields() {
        txtISBN.clear();
        txtTitle.clear();
        txtSubject.clear();
        txtPublisher.clear();
        datePublicationDate.setValue(null);
        txtLanguage.clear();
        txtNumberOfPages.clear();
        txtAuthorId.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
