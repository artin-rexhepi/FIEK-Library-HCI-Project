package controller;

import app.SessionManager;
import javafx.event.ActionEvent;
import javafx.event.Event;
import app.Navigator;
import model.User;
import model.dto.LoginUserDto;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.UserService;

import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController {

    private UserService userService;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField pwdPassword;

    @FXML
    private Text loginMessage;

    @FXML
    private Button logIn;

    @FXML
    private ImageView albanianFlag;

    @FXML
    private ImageView americanFlag;

    @FXML
    private Text bibliotekaText;

    @FXML
    private Text facultyText;

    @FXML
    private Text universityText;

    @FXML
    private Text cityText;


    // Setter for userService, allows injection after FXML loading
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @FXML
    public void initialize() {
        // Add key listeners to the text fields
        txtUsername.setOnKeyPressed(this::handleUsernameKeyPressed);
        pwdPassword.setOnKeyPressed(this::handlePasswordKeyPressed);

        // Set initial locale to Albanian
        Locale.setDefault(new Locale("sq"));
        translateAlbanian();

        // Set flag click event handlers
        albanianFlag.setOnMouseClicked(e -> translateAlbanian());
        americanFlag.setOnMouseClicked(e -> translateEnglish());

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

            User user = userService.getUserByUsername(username);
            SessionManager.getInstance().setCurrentUser(user);

            Stage stage = (Stage) (event != null ? ((Node) event.getSource()).getScene().getWindow() : txtUsername.getScene().getWindow());
            Navigator.navigate(stage, Navigator.ADMIN_PAGE);
        } else {
            loginMessage.setText("Invalid username or password.");
        }
    }
    @FXML
    void translateEnglish() {
        Locale currentLocale = new Locale("en");
        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);

        if (logIn != null && bibliotekaText != null && facultyText != null && universityText != null &&
                cityText != null) {

            logIn.setText(translate.getString("button.logIn"));
            bibliotekaText.setText(translate.getString("text.bibliotekaText"));
            facultyText.setText(translate.getString("text.facultyText"));
            universityText.setText(translate.getString("text.universityText"));
            cityText.setText(translate.getString("text.cityText"));

        }
    }

    @FXML
    void translateAlbanian() {
        Locale currentLocale = new Locale("sq");
        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);

        if (logIn != null && bibliotekaText != null && facultyText != null && universityText != null &&
                cityText != null) {

            logIn.setText(translate.getString("button.logIn"));
            bibliotekaText.setText(translate.getString("text.bibliotekaText"));
            facultyText.setText(translate.getString("text.facultyText"));
            universityText.setText(translate.getString("text.universityText"));
            cityText.setText(translate.getString("text.cityText"));

        }
    }

}
