import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

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

    @FXML
    public void handleFshij() {
        clearFields();
    }

    @FXML
    public void handleRuaj() {
        // Retrieve member details from UI components
        String emri = txtEmri.getText();
        String email = txtEmail.getText();
        String numerTelefon = txtNumerTelefon.getText();
        String id = txtId.getText();
        String gjinia = txtGjinia.getText();

        // Create a MemberDTO object with the retrieved data
        MemberDTO memberDTO = new MemberDTO(emri, email, numerTelefon, id, gjinia);

        // Call the service method to register the member
        boolean isRegistered = memberService.registerMember(memberDTO);

        if (isRegistered) {
            // Member registration successful, display a success message
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Anëtari është regjistruar me sukses!");
            clearFields();
        } else {
            // Member registration failed, display an error message
            showAlert(Alert.AlertType.ERROR, "Error", "Dështim në regjistrimin e anëtarit");
        }
    }

    private void clearFields() {
        txtEmri.clear();
        txtEmail.clear();
        txtNumerTelefon.clear();
        txtId.clear();
        txtGjinia.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

