package controller;

import app.Navigator;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Member;
import model.dto.MemberDto;
import model.filter.MemberFilter;
import repository.AdminRepository;
import service.AdminService;
import service.DBConnector;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {

    private AdminService adminService;


    @FXML
    private Text txtPershendetje;

    @FXML
    private Text txtHyrja;


    @FXML
    private Button btnBack; // Deklarimi i btnBack
    @FXML
    private TableView<MemberDto> tableMenaxhimiPerdoruesve;

    @FXML
    private TableColumn<MemberDto, String> tablecol_email;

    @FXML
    private TableColumn<MemberDto, String> tablecol_gender;

    @FXML
    private TableColumn<MemberDto, String> tablecol_id;

    @FXML
    private TableColumn<MemberDto, String> tablecol_name;

    @FXML
    private TableColumn<MemberDto, String> tablecol_phone;

    private ObservableList<MemberDto> memberList;


    @FXML
    private Button btnHuazuar;


    @FXML
    private TextField idFilter;
    @FXML
    private TextField emriFilter;
    @FXML
    private TextField emailFilter;
    @FXML
    private TextField phoneFilter;
    @FXML
    private TextField gjiniaFilter;


    public AdminPageController(){
        this.adminService=new AdminService();

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
// Vendosja e veprimit për butonin "Huazuar"
        if (btnHuazuar != null) {
            btnHuazuar.setOnAction(event -> {
                try {
                    handleHuazuarClick(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
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


    @FXML
    public void shfaqPerdorues(ActionEvent event) {
        adminService.setMembers(tablecol_id, "IDstudendore");
        adminService.setMembers(tablecol_name, "emri");
        adminService.setMembers(tablecol_email, "email");
        adminService.setMembers(tablecol_phone, "numerTelefoni");
        adminService.setMembers(tablecol_gender, "gjinia");
        memberList = adminService.getMemberData();
        tableMenaxhimiPerdoruesve.setItems(memberList);
    }
    @FXML
    public void shfaqPerdoruesHuazuar(ActionEvent event) {
        adminService.setMembers(tablecol_id, "IDstudendore");
        adminService.setMembers(tablecol_name, "emri");
        adminService.setMembers(tablecol_email, "email");
        adminService.setMembers(tablecol_phone, "numerTelefoni");
        adminService.setMembers(tablecol_gender, "gjinia");
        memberList = adminService.getIssuedBookMember();
        tableMenaxhimiPerdoruesve.setItems(memberList);
    }
    @FXML
    public void filtroPerdorues(ActionEvent event) {
        String id = idFilter.getText();
        String name = emriFilter.getText();
        String email = emailFilter.getText();
        String phone = phoneFilter.getText();
        String gender = gjiniaFilter.getText();

        MemberFilter memberFilter = new MemberFilter(id, name, email, phone, gender);

        memberList = adminService.filter(memberFilter);
        tableMenaxhimiPerdoruesve.setItems(memberList);
    }


    @FXML
    private void handleHuazuarClick(ActionEvent event) throws IOException {
        Navigator.navigate((Stage) ((Node) event.getSource()).getScene().getWindow(), Navigator.HUAZUAR_BOOKS_PAGE);
    }


}
