package controller;

import model.dto.LoginUserDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import service.UserService;

public class LoginController {

    private UserService userService;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField pwdPassword;

    @FXML
    private Text loginMessage;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @FXML
    public void handleLoginClick(ActionEvent event) {
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
            // Navigate to the next screen or dashboard
        } else {
            loginMessage.setText("Invalid username or password.");
        }
    }
}
