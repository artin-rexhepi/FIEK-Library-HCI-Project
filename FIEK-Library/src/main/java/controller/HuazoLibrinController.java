package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.dto.IssuedBookDto;
import org.w3c.dom.Text;
import service.IssuedBooksService;
import app.Navigator;

import java.sql.Timestamp;
import java.util.Locale;
import java.util.ResourceBundle;

public class HuazoLibrinController {

        @FXML
        private Button btnBack;

        @FXML
        private Button btnFshij;
        @FXML
        private Button btnRuaj;
    @FXML
    private Button btnRuaj1;
    @FXML
    private Button btnFshij1;
    @FXML
    private Label Huazimi;
    @FXML
    private Label Kthelibra;
    @FXML
    private ImageView albanianFlag;

    @FXML
    private ImageView americanFlag;
    @FXML
    private ImageView albanianFlag1;

    @FXML
    private ImageView americanFlag1;
    @FXML
    private TextField txtIDStudentore;

    @FXML
    private TextField txtIDLibrit;
   ;

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
    private TextField txtIDStudentore1;

    @FXML
    private TextField txtIDLibrit1;

    // Existing methods...

    @FXML
    private void handleRuaj1() {
        String memberId = txtIDStudentore1.getText();
        String isbn = txtIDLibrit1.getText();
        Timestamp issueTime = new Timestamp(System.currentTimeMillis());
        int renewCount = 0;  // Reset renew count

        // Create an IssuedBookDto object with the retrieved data
        IssuedBookDto issuedBookDto = new IssuedBookDto(isbn, memberId, issueTime, renewCount);

        // Call the service method to return the book
        boolean isReturned = issuedBooksService.returnBook(issuedBookDto);

        if (isReturned) {
            // Book returned successfully, display a success message
            showAlert(Alert.AlertType.INFORMATION, "Success", "Book returned successfully!");
            clearReturnFields();
        } else {
            // Returning book failed, display an error message
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to return book");
        }
    }

    private void clearReturnFields() {
        txtIDStudentore1.clear();
        txtIDLibrit1.clear();
    }
    @FXML
    private void handleFshij1() {
        clearReturnFields();
    }

    private void goBack() {
        Navigator.navigate(btnBack, Navigator.MANAGE_BOOKS_OPTIONS);
    }
    private void goBack1() {
        Navigator.navigate(btnBack, Navigator.MANAGE_BOOKS_OPTIONS);
    }

    @FXML
    public void handleReturn() {
        // Get the current stage from any of the text fields
        Stage stage = (Stage) txtIDStudentore.getScene().getWindow();
        // Navigate to the MANAGE_USERS page
        Navigator.navigate(stage, Navigator.MANAGE_BOOKS_OPTIONS);
        goBack();
        goBack1();
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
        // Set initial locale to Albanian
        Locale.setDefault(new Locale("sq"));
        translateAlbanian();

        // Set flag click event handlers
        albanianFlag.setOnMouseClicked(e -> translateAlbanian());
        americanFlag.setOnMouseClicked(e -> translateEnglish());
        albanianFlag1.setOnMouseClicked(e -> translateAlbanian());
        americanFlag1.setOnMouseClicked(e -> translateEnglish());
    }
    @FXML
    void translateEnglish() {
        Locale currentLocale = new Locale("en");
        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);

        btnFshij.setText(translate.getString("button.btnFshij"));
        btnRuaj.setText(translate.getString("button.btnRuaj"));
        btnFshij1.setText(translate.getString("button.btnFshij1"));
        btnRuaj1.setText(translate.getString("button.btnRuaj1"));

        txtIDStudentore.setPromptText(translate.getString("textfield.txtIDStudentore"));
        txtIDLibrit.setPromptText(translate.getString("textfield.txtIDLibrit"));
        txtIDStudentore1.setPromptText(translate.getString("textfield.txtIDStudentore1"));
        txtIDLibrit1.setPromptText(translate.getString("textfield.txtIDLibrit1"));

        Huazimi.setText(translate.getString("text.Huazimi"));
        Kthelibra.setText(translate.getString("text.Kthelibra"));
    }

    @FXML
    void translateAlbanian() {
        Locale currentLocale = new Locale("sq");
        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);


        btnFshij.setText(translate.getString("button.btnFshij"));
        btnRuaj.setText(translate.getString("button.btnRuaj"));
        btnFshij1.setText(translate.getString("button.btnFshij1"));
        btnRuaj1.setText(translate.getString("button.btnRuaj1"));

        txtIDStudentore.setPromptText(translate.getString("textfield.txtIDStudentore"));
        txtIDLibrit.setPromptText(translate.getString("textfield.txtIDLibrit"));
        txtIDStudentore1.setPromptText(translate.getString("textfield.txtIDStudentore1"));
        txtIDLibrit1.setPromptText(translate.getString("textfield.txtIDLibrit1"));

        Huazimi.setText(translate.getString("text.Huazimi"));
        Kthelibra.setText(translate.getString("text.Kthelibra"));
    };
    }


