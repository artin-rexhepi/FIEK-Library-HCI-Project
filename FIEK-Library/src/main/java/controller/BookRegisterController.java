package controller;

import app.Navigator;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import model.dto.BookDTO;
import service.BookService;

import java.util.Locale;
import java.util.ResourceBundle;

public class BookRegisterController {

    private BookService bookService;

    @FXML
    private TextField txtISBN;
    @FXML
    private Label regjistrimi;

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
    private Button btnBack;
    @FXML
    private ImageView albanianFlag;

    @FXML
    private ImageView americanFlag;
    @FXML
    private Button btnRuaj;
    @FXML
    private Button btnFshij;



    public BookRegisterController() {
        this.bookService = new BookService();
    }



    @FXML
    private void handleBookRegisterSave() {
        String ISBN = txtISBN.getText();
        String title = txtTitle.getText();
        String subject = txtSubject.getText();
        String publisher = txtPublisher.getText();
        String quantity = txtQuantity.getText();
        String author = txtAuthor.getText();

        if (!validateInput(ISBN, title, subject, publisher, quantity, author)) {
            return;
        }

        BookDTO bookDTO = new BookDTO(ISBN, title, subject, publisher, quantity, author);

        boolean isRegistered = bookService.registerBook(bookDTO);

        if (isRegistered) {
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Libri është regjistruar me sukses!");
            clearFields();
        } else {
            showAlert(Alert.AlertType.ERROR, "Gabim", "Dështoi regjistrimi i librit");
        }
    }

    @FXML
    private void handleBookRegisterCancel() {
        clearFields();
    }

    private boolean validateInput(String ISBN, String title, String subject, String publisher, String quantity, String author) {
        if (ISBN.isEmpty() || title.isEmpty() || subject.isEmpty() || publisher.isEmpty() || quantity.isEmpty() || author.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Gabim", "Ju lutem plotësoni të gjitha fushat");
            return false;
        }
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

    private void goBack() {
        Navigator.navigate(btnBack, Navigator.MANAGE_BOOKS_OPTIONS);
    }

    @FXML
    public void handleReturn() {
        goBack();
    }



    @FXML
    public void initialize() {
        btnBack.setOnAction(event -> handleReturn());

        // Shtimi i event handler për kalimin me Enter neper textfielda
        txtISBN.setOnAction(event -> txtTitle.requestFocus());
        txtTitle.setOnAction(event -> txtPublisher.requestFocus());
        txtPublisher.setOnAction(event -> txtSubject.requestFocus());
        txtSubject.setOnAction(event -> txtAuthor.requestFocus());
        txtAuthor.setOnAction(event -> txtQuantity.requestFocus());
        txtQuantity.setOnAction(event -> {
            handleBookRegisterSave();
            event.consume(); // Për të parandaluar që shtypja e Enter të shkaktojë një ngjarje të tjera si kalimi në fushën tjetër


        });
        // Set initial locale to Albanian
        Locale.setDefault(new Locale("sq"));
        translateAlbanian();

        // Set flag click event handlers
        albanianFlag.setOnMouseClicked(e -> translateAlbanian());
        americanFlag.setOnMouseClicked(e -> translateEnglish());
    }
    @FXML
    void translateEnglish() {
        Locale currentLocale = new Locale("en");
        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);

        if (regjistrimi != null && txtISBN != null && txtTitle != null && txtPublisher != null && txtSubject != null && txtAuthor != null && txtQuantity != null && btnFshij != null && btnRuaj != null) {
            regjistrimi.setText(translate.getString("text.regjistrimi"));
            txtISBN.setPromptText(translate.getString("label.isbn"));
            txtTitle.setPromptText(translate.getString("label.title"));
            txtPublisher.setPromptText(translate.getString("label.publisher"));
            txtSubject.setPromptText(translate.getString("label.subject"));
            txtAuthor.setPromptText(translate.getString("label.author"));
            txtQuantity.setPromptText(translate.getString("label.quantity"));
            btnFshij.setText(translate.getString("button.cancel"));
            btnRuaj.setText(translate.getString("button.save"));
        }
    }

    @FXML
    void translateAlbanian() {
        Locale currentLocale = new Locale("sq");
        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);

        if (regjistrimi != null && txtISBN != null && txtTitle != null && txtPublisher != null && txtSubject != null && txtAuthor != null && txtQuantity != null && btnFshij != null && btnRuaj != null) {
            regjistrimi.setText(translate.getString("text.regjistrimi"));
            txtISBN.setPromptText(translate.getString("label.isbn"));
            txtTitle.setPromptText(translate.getString("label.title"));
            txtPublisher.setPromptText(translate.getString("label.publisher"));
            txtSubject.setPromptText(translate.getString("label.subject"));
            txtAuthor.setPromptText(translate.getString("label.author"));
            txtQuantity.setPromptText(translate.getString("label.quantity"));
            btnFshij.setText(translate.getString("button.cancel"));
            btnRuaj.setText(translate.getString("button.save"));
        }
    }

}
