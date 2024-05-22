package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.dto.MemberDto;
import service.MemberService;
import app.Navigator;

public class AddMemberController {

    @FXML
    private TextField txtEmri;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtNumerTelefon;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtGjinia;

    private MemberService memberService;

    public AddMemberController() {
        // Instantiate the MemberService
        this.memberService = new MemberService();
    }

    @FXML
    private void handleRuaj() {
        String id = txtId.getText();
        String emri = txtEmri.getText();
        String email = txtEmail.getText();
        String numerTelefoni = txtNumerTelefon.getText();
        String gjinia = txtGjinia.getText();

        // Create a MemberDto object with the retrieved data
        MemberDto memberDto = new MemberDto(id, emri, email, numerTelefoni, gjinia);

        // Call the service method to save the member
        boolean isSaved = memberService.registerMember(memberDto);

        if (isSaved) {
            // Member saved successfully, display a success message
            showAlert(Alert.AlertType.INFORMATION, "Success", "Member saved successfully!");
            clearFields();
        } else {
            // Saving member failed, display an error message
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to save member");
        }
    }

    @FXML
    private void handleFshij() {
        clearFields();
    }

    private void clearFields() {
        txtId.clear();
        txtEmri.clear();
        txtEmail.clear();
        txtGjinia.clear();
        txtNumerTelefon.clear();
    }

    @FXML
    public void handleReturn() {
        // Get the current stage from any of the text fields
        Stage stage = (Stage) txtEmri.getScene().getWindow();
        // Navigate to the CREATE_ACCOUNT_PAGE
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
        txtId.setOnAction(event -> txtEmri.requestFocus());
        txtEmri.setOnAction(event -> txtEmail.requestFocus());
        txtEmail.setOnAction(event -> txtGjinia.requestFocus());
        txtGjinia.setOnAction(event -> txtNumerTelefon.requestFocus());
        txtNumerTelefon.setOnAction(event -> {
            handleRuaj();
            event.consume(); // Për të parandaluar që shtypja e Enter të shkaktojë një ngjarje të tjera si kalimi në fushën tjetër

        });
    }

}
