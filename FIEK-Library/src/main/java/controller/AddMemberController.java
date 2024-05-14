package controller;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.dto.MemberDto;
import repository.MemberRepository;

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

    private MemberRepository memberRepository;

    public AddMemberController() {
        // Instantiate the MemberRepository
        this.memberRepository = new MemberRepository();
    }

    @FXML
    private void handleRuaj() {
        // Retrieve member details from UI components
        String id = txtId.getText();
        String emri = txtEmri.getText();
        String email = txtEmail.getText();
        String gjinia = txtGjinia.getText();
        String numerTelefoni = txtNumerTelefon.getText();

        // Create a MemberDto object with the retrieved data
        MemberDto memberDto = new MemberDto(id, emri, email, gjinia, numerTelefoni);

        // Call the repository method to save the member
        boolean isSaved = memberRepository.create(memberDto);

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

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
