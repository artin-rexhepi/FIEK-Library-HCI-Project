package controller;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import repository.AdminRepository;
import service.DBConnector;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {
    private AdminRepository adminRepository;

    @FXML
    private Text txtPershendetje;

    @FXML
    private Text txtHyrja;

    @FXML
    private Button btnEditoLibra;

    @FXML
    private Button btnEditoPerdorues;

    @FXML
    private Button btnBack; // Deklarimi i btnBack

    public AdminPageController() {
        this.adminRepository = new AdminRepository();
    }

    @FXML
    private void editolibra(ActionEvent ae) throws IOException {
        Navigator.navigate((Stage) ((Node) ae.getSource()).getScene().getWindow(), Navigator.MANAGE_BOOKS_OPTIONS);
    }

    @FXML
    private void editoperdorues(ActionEvent ae) throws IOException {
        Navigator.navigate((Stage) ((Node) ae.getSource()).getScene().getWindow(), Navigator.MANAGE_USERS);
    }

    @FXML
    private void regjistroPerdorues(ActionEvent ae) throws IOException {
        Navigator.navigate((Stage) ((Node) ae.getSource()).getScene().getWindow(), Navigator.CREATE_ACCOUNT_PAGE);
    }

    @FXML
    private void handleShtoLibra(ActionEvent ae) {
        Navigator.navigate((Stage) ((Node) ae.getSource()).getScene().getWindow(), Navigator.REGISTER_BOOK_PAGE);
    }

    @FXML
    private void fshijLibra(ActionEvent ae) throws IOException {
        Navigator.navigate((Stage) ((Node) ae.getSource()).getScene().getWindow(), Navigator.DELETE_BOOK_PAGE);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //            String userName = adminRepository.fetchUserNameFromDatabase();
//            txtPershendetje.setText("Pershendetje " + userName);
        if (txtPershendetje != null) {
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

        // Lidhja e btnBack me metodën handleReturn
        if (btnBack != null) {
            btnBack.setOnAction(event -> handleReturn());
        } else {
            System.err.println("Button 'btnBack' eshte null.");
        }
        // Lidhja e btnBack me metodën handleReturn
        if (btnBack != null) {
            btnBack.setOnAction(event -> handleReturn1());
        } else {
            System.err.println("Button 'btnBack' eshte null.");
        }
    }

    @FXML
    public void handleReturn() {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        // Kthehu në panelin e menaxhimit të përdoruesve
        Navigator.navigate(stage, Navigator.MANAGE_BOOKS_OPTIONS);
    }
    public void handleReturn1() {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        // Kthehu në panelin e menaxhimit të përdoruesve
        Navigator.navigate(stage, Navigator.ADMIN_PAGE);
    }

}
