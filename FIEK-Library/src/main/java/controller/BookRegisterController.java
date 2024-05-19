package controller;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dto.BookDTO;
import service.BookService;

import java.io.IOException;

public class BookRegisterController {

    private BookService bookService;

    // Deklarimi i kontrollit të fushave të tekstualizuara
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

    public BookRegisterController() {
        // Inicializimi i shërbimit të librit
        this.bookService = new BookService();
    }

    @FXML
    private void handleShtoLibra() {
        // Thirrja e metodes për shtimin e librave (kthimin pas)
        goBack();
    }

    @FXML
    private void handleBookRegisterSave() {
        // Marrja e të dhënave të librit nga komponentet e ndërfaqes së përdoruesit
        String ISBN = txtISBN.getText();
        String title = txtTitle.getText();
        String subject = txtSubject.getText();
        String publisher = txtPublisher.getText();
        String quantity = txtQuantity.getText();
        String author = txtAuthor.getText();

        // Validimi i të dhënave
        if (!validateInput(ISBN, title, subject, publisher, quantity, author)) {
            return;
        }

        // Krijimi i një objekti BookDTO me të dhënat e marrë
        BookDTO bookDTO = new BookDTO(ISBN, title, subject, publisher, quantity, author);

        // Thirrja e metodes se shërbimit për të regjistruar librin
        boolean isRegistered = bookService.registerBook(bookDTO);

        if (isRegistered) {
            // Njoftimi i suksesit në regjistrimin e librit
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Libri është regjistruar me sukses!");
            // Fshirja e fushave pas regjistrimit të suksesshëm
            clearFields();
        } else {
            // Njoftimi i dështimit në regjistrimin e librit
            showAlert(Alert.AlertType.ERROR, "Gabim", "Dështoi regjistrimi i librit");
        }
    }

    @FXML
    private void handleBookRegisterCancel() {
        // Fshirja e fushave pas anulimit të regjistrimit të librit
        clearFields();
        // Kthimi në panelin e mëparshëm
        goBack();
    }

    private boolean validateInput(String ISBN, String title, String subject, String publisher, String quantity, String author) {
        // Validimi i të dhënave të hyra nga përdoruesi
        if (ISBN.isEmpty() || title.isEmpty() || subject.isEmpty() || publisher.isEmpty() || quantity.isEmpty() || author.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Gabim", "Ju lutem plotësoni të gjitha fushat");
            return false;
        }

        // Logjika e validimit shtesë mund të shtohet këtu

        return true;
    }

    // Metoda për të pastër fushat e hyrjes pas regjistrimit të suksesshëm të librit
    private void clearFields() {
        txtISBN.clear();
        txtTitle.clear();
        txtSubject.clear();
        txtPublisher.clear();
        txtQuantity.clear();
        txtAuthor.clear();
    }

    // Metoda për të treguar një njoftim në dialog
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Metoda për të kthyer në panelin e mëparshëm
    private void goBack() {
        // Kthimi në panelin e mëparshëm
        Stage stage = (Stage) txtISBN.getScene().getWindow();
        stage.close();
        // Këtu mund të shtoni kodin për të kthyer në panelin e mëparshëm
    }


    public void kthePrapa(ActionEvent actionEvent) {
    }
}
