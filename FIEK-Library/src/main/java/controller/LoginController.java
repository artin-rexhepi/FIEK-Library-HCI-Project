package controller;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;
import model.dto.LoginUserDto;
import service.UserService;

public class LoginController {

    private UserService userService;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField pwdPassword;

    @FXML
    private Text loginMessage;


    // Setter for userService, allows injection after FXML loading
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @FXML
    public void initialize() {
        // Add key listeners to the text fields
        txtUsername.setOnKeyPressed(this::handleUsernameKeyPressed);
        pwdPassword.setOnKeyPressed(this::handlePasswordKeyPressed);
    }

    private void handleUsernameKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            pwdPassword.requestFocus();
        }
    }

    private void handlePasswordKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleLoginClick(null); // Passing null because ActionEvent is not needed here
        }
    }

    @FXML
    public void handleLoginClick(ActionEvent event) {
        if (userService == null) {
            loginMessage.setText("Service not initialized.");
            return;
        }

        String username = txtUsername.getText();
        String password = pwdPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            loginMessage.setText("Username and Password are required.");
            return;
        }

        LoginUserDto loginUserDto = new LoginUserDto(username, password);
        boolean isAuthenticated = userService.authenticate(loginUserDto);

        if (isAuthenticated) {
            loginMessage.setText("Login successful.");
            Stage stage = (Stage) (event != null ? ((Node) event.getSource()).getScene().getWindow() : txtUsername.getScene().getWindow());
            Navigator.navigate(stage, Navigator.ADMIN_PAGE);
        } else {
            loginMessage.setText("Invalid username or password.");
        }

    }
}
