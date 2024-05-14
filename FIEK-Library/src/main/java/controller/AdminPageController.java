package controller;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import repository.AdminRepository;
import service.DBConnector;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AdminPageController {
    private AdminRepository adminRepository;
    @FXML
    private Text txtAdminPage;
    @FXML
    private Text txtPershendetje;
    @FXML
    private Text txtHyrja;
    @FXML
    private TableView<String> tabela;
    @FXML
    private Button btnEditoLibra;
    @FXML
    private Button btnEditoPerdorues;

    public AdminPageController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    @FXML
    private void editolibra(ActionEvent ae) throws IOException {
//        Navigator.navigate(ae, Navigator.REGISTER_BOOK_PAGE);
    }

    @FXML
    private void editoperdorues(ActionEvent ae) throws IOException{
//        Navigator.navigate(ae, Navigator.REGISTER_USER_PAGE);
    }

    @FXML
    private void initialize() {
        if (txtPershendetje != null) {
            int loggedInUserId = adminRepository.getLoggedInUserId();
            String userName = adminRepository.fetchUserNameFromDatabase(loggedInUserId);
            txtPershendetje.setText("Pershendetje " + userName);
        } else {
            System.err.println("Text objekti 'Pershendetje' eshte null.");
        }

        if (txtHyrja != null) {
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedTime = currentTime.format(formatter);
            txtHyrja.setText("Hyrja: " + formattedTime);
        } else {
            System.err.println("Text objekti 'Hyrja' eshte null.");
        }
    }

}
