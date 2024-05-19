package controller;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
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
    private Text txtPershendetje;
    @FXML
    private Text txtHyrja;
    @FXML
    private Button btnEditoLibra;
    @FXML
    private Button btnEditoPerdorues;


    public AdminPageController() {
        this.adminRepository = new AdminRepository();
    }
    @FXML
    private void editolibra(ActionEvent ae) throws IOException {
        Navigator.navigate((Stage) ((Node) ae.getSource()).getScene().getWindow(), Navigator.MANAGE_BOOKS_OPTIONS);
    }

    @FXML
    private void editoperdorues(ActionEvent ae) throws IOException{
        Navigator.navigate((Stage) ((Node) ae.getSource()).getScene().getWindow(), Navigator.MANAGE_USERS);
    }

    @FXML
    private void regjistroPerdorues(ActionEvent ae) throws IOException{
        Navigator.navigate((Stage) ((Node) ae.getSource()).getScene().getWindow(), Navigator.CREATE_ACCOUNT_PAGE);
    }

    @FXML
    private void handleShtoLibra(ActionEvent ae) {
        Navigator.navigate((Stage) ((Node) ae.getSource()).getScene().getWindow(), Navigator.REGISTER_BOOK_PAGE);
    }



    @FXML
    private void initialize() {
        if (txtPershendetje != null) {
//            String userName = adminRepository.fetchUserNameFromDatabase();
//            txtPershendetje.setText("Pershendetje " + userName);
            txtPershendetje.setText("Pershendetje!");
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
