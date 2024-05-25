package controller;

import app.Navigator;
import app.SessionManager;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Member;
import model.User;
import model.dto.BookDTO;
import model.dto.MemberDto;
import model.filter.MemberFilter;
import repository.AdminRepository;
import service.AdminService;
import service.DBConnector;
import java.io.IOException;
import java.net.URL;
import java.text.CollationElementIterator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {

    private AdminService adminService;


    @FXML
    private Text txtPershendetje;

    @FXML
    private Text txtHyrja;
    @FXML
    private Text Paneli;
    @FXML
    private Text txtPaneliAdminit;
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

     @FXML
    private Text title_management_panel_books;
@FXML
private Button btnEditoPerdorues2;

    @FXML
    private Button btnEditoLibra;

    @FXML
    private Button btnEditoPerdorues;

    @FXML
    private Button btnEditoLibra1;

    @FXML
    private Button btnEditoLibra11;

    @FXML
    private Button btnEditoLibra121;

    @FXML
    private Button btnEditoLibra1211;

    @FXML
    private Button btnShtoLibra;

    @FXML
    private Button btnEditoLibra121211;
    @FXML
    private Button shfaq;

    @FXML
    private Button shfaqhuazuar;

    @FXML
    private Button regjistroperdorues;

    @FXML
    private Button filtro;

    @FXML
    private ImageView albanianFlag;

    @FXML
    private ImageView americanFlag;
    @FXML
    private TableColumn<BookDTO, String> colAutori;
    @FXML
    private TableColumn<BookDTO, String> colISBN;
    @FXML
    private TableColumn<BookDTO, String> colTitulli;
    @FXML
    private TableColumn<BookDTO, String> colPublikuesi;
    @FXML
    private TableColumn<BookDTO, String> colDataPublikimit;

    @FXML
    private Text idTextFilter;
    @FXML
    private Text emriTextFilter;
    @FXML
    private Text telTextFilter;
    @FXML
    private Text emailTextFilter;
    @FXML
    private Text gjiniaTextFilter;


    public AdminPageController(){
        this.adminService=new AdminService();
    }

    @FXML
    private void editolibra(ActionEvent ae) throws IOException {
        Navigator.navigate((Stage) ((Node) ae.getSource()).getScene().getWindow(), Navigator.MANAGE_BOOKS_OPTIONS);
    }
    @FXML
    private void editolibra1(ActionEvent ae) throws IOException {
        Navigator.navigate((Stage) ((Node) ae.getSource()).getScene().getWindow(), Navigator.HUAZO_LIBRIN);
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
    @FXML
    private void handleHuazuarClick(ActionEvent event) throws IOException {
        Navigator.navigate((Stage) ((Node) event.getSource()).getScene().getWindow(), Navigator.HUAZUAR_BOOKS_PAGE);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        {

            // Set initial locale to Albanian
            Locale.setDefault(new Locale("sq"));
           // translateAlbanian();

            // Set flag click event handlers
            albanianFlag.setOnMouseClicked(e -> translateAlbanian());
            americanFlag.setOnMouseClicked(e -> translateEnglish());
        }

        User currentUser = SessionManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            String username = currentUser.getUsername();
            if (txtPershendetje != null) {
                txtPershendetje.setText("Mirë se vini, " + username + "!");
            }
        }
        if (txtHyrja != null) {
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formattedTime = currentTime.format(formatter);
            txtHyrja.setText("Hyrja: " + formattedTime);
        }

        // Lidhja e btnBack me metodën handleReturn
        if (btnBack != null) {
            btnBack.setOnAction(event -> handleReturn());
        }
        // Lidhja e btnBack me metodën handleReturn
        if (btnBack != null) {
            btnBack.setOnAction(event -> handleReturn1());
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
        adminService.setMembers(tablecol_id, "IDstudendore");
        adminService.setMembers(tablecol_name, "emri");
        adminService.setMembers(tablecol_email, "email");
        adminService.setMembers(tablecol_phone, "numerTelefoni");
        adminService.setMembers(tablecol_gender, "gjinia");

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
    void translateEnglish() {
        Locale currentLocale = new Locale("en");
        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);
    //Duhet te rishikohet


        //Adminpage
        User currentUser = SessionManager.getInstance().getCurrentUser();
        String username = currentUser.getUsername();
        if(txtPershendetje != null){
            txtPershendetje.setText(translate.getString("text.txtPershendetje") +" "+ username + "!");
        }

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        if(txtHyrja != null){
            txtHyrja.setText(translate.getString("text.txtHyrja") +" "+ formattedTime);
        }
        if(txtPaneliAdminit != null){
            txtPaneliAdminit.setText("   " + translate.getString("text.txtPaneliAdminit"));
        }

        // Përkthimet për butonat
        if(btnEditoLibra1 != null && btnEditoPerdorues2 != null){
            btnEditoLibra1.setText(translate.getString("button.btnEditoLibra1"));
            btnEditoPerdorues2.setText(translate.getString("button.btnEditoPerdorues2"));
        }



        // Përditëso tekst për tabelën
        if(colISBN != null && colTitulli != null && colAutori != null && colPublikuesi != null && colDataPublikimit != null){
            colISBN.setText(translate.getString("column.colISBN"));
            colTitulli.setText(translate.getString("column.colTitulli"));
            colAutori.setText(translate.getString("column.colAutori"));
            colPublikuesi.setText(translate.getString("column.colPublikuesi"));
            colDataPublikimit.setText(translate.getString("column.colDataPublikimit"));
        }
        if(title_management_panel_books != null && btnEditoLibra != null && btnEditoPerdorues != null && btnEditoLibra1 != null && btnEditoLibra11 != null && btnHuazuar != null && btnEditoLibra121 != null && btnEditoLibra1211 != null && btnShtoLibra != null && btnEditoLibra121211 != null){
            title_management_panel_books.setText(translate.getString("text.title_management_panel_books"));
            btnEditoLibra.setText(translate.getString("button.btnEditoLibra_manage_books"));
            btnEditoPerdorues.setText(translate.getString("button.btnEditoPerdorues_manage_users"));
            btnEditoLibra1.setText(translate.getString("button.btnEditoLibra1_list_all_books"));
            btnEditoLibra11.setText(translate.getString("button.btnEditoLibra11_filter"));
            btnHuazuar.setText(translate.getString("button.btnHuazuar"));
            btnEditoLibra121.setText(translate.getString("button.btnEditoLibra121"));
            btnEditoLibra1211.setText(translate.getString("button.btnEditoLibra1211"));
            btnShtoLibra.setText(translate.getString("button.btnShtoLibra"));
            btnEditoLibra121211.setText(translate.getString("button.btnEditoLibra121211"));
        }


        //manageusers column
        if(tablecol_id != null && tablecol_name != null && tablecol_email != null && tablecol_phone != null && tablecol_gender != null){
            tablecol_id.setText(translate.getString("column.tablecol_id"));
            tablecol_name.setText(translate.getString("column.tablecol_name"));
            tablecol_email.setText(translate.getString("column.tablecol_email"));
            tablecol_phone.setText(translate.getString("column.tablecol_phone"));
            tablecol_gender.setText(translate.getString("column.tablecol_gender"));
        }
        if(Paneli != null && shfaq != null && shfaqhuazuar != null && regjistroperdorues != null && filtro != null){
            Paneli.setText(translate.getString("text.Paneli"));
            shfaq.setText(translate.getString("button.shfaq"));
            shfaqhuazuar.setText(translate.getString("button.shfaqhuazuar"));
            regjistroperdorues.setText(translate.getString("button.regjistroperdorues"));
            filtro.setText(translate.getString("button.filtro"));
        }


        if(idTextFilter != null && emriTextFilter != null && emailTextFilter != null && telTextFilter != null && gjiniaTextFilter != null){
            idTextFilter.setText(translate.getString("text.idTextFilter"));
            emriTextFilter.setText(translate.getString("text.emriTextFilter"));
            emailTextFilter.setText(translate.getString("text.emailTextFilter"));
            telTextFilter.setText(translate.getString("text.telTextFilter"));
            gjiniaTextFilter.setText(translate.getString("text.gjiniaTextFilter"));
        }

    }
    @FXML
    void translateAlbanian(){
            Locale currentLocale = new Locale("sq");
            ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);

            User currentUser = SessionManager.getInstance().getCurrentUser();
            String username = currentUser.getUsername();

            //Adminpage
        if(txtPershendetje != null){
            txtPershendetje.setText(translate.getString("text.txtPershendetje") +" "+ username + "!");
        }

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        if(txtHyrja != null){
            txtHyrja.setText(translate.getString("text.txtHyrja") +" "+ formattedTime);
        }
        if(txtPaneliAdminit != null){
            txtPaneliAdminit.setText(translate.getString("text.txtPaneliAdminit"));
        }

        // Përkthimet për butonat
        if(btnEditoLibra1 != null && btnEditoPerdorues2 != null){
            btnEditoLibra1.setText(translate.getString("button.btnEditoLibra1"));
            btnEditoPerdorues2.setText(translate.getString("button.btnEditoPerdorues2"));
        }



        // Përditëso tekst për tabelën
        if(colISBN != null && colTitulli != null && colAutori != null && colPublikuesi != null && colDataPublikimit != null){
            colISBN.setText(translate.getString("column.colISBN"));
            colTitulli.setText(translate.getString("column.colTitulli"));
            colAutori.setText(translate.getString("column.colAutori"));
            colPublikuesi.setText(translate.getString("column.colPublikuesi"));
            colDataPublikimit.setText(translate.getString("column.colDataPublikimit"));
        }
        if(title_management_panel_books != null && btnEditoLibra != null && btnEditoPerdorues != null && btnEditoLibra1 != null && btnEditoLibra11 != null && btnHuazuar != null && btnEditoLibra121 != null && btnEditoLibra1211 != null && btnShtoLibra != null && btnEditoLibra121211 != null){
            title_management_panel_books.setText(translate.getString("text.title_management_panel_books"));
            btnEditoLibra.setText(translate.getString("button.btnEditoLibra_manage_books"));
            btnEditoPerdorues.setText(translate.getString("button.btnEditoPerdorues_manage_users"));
            btnEditoLibra1.setText(translate.getString("button.btnEditoLibra1_list_all_books"));
            btnEditoLibra11.setText(translate.getString("button.btnEditoLibra11_filter"));
            btnHuazuar.setText(translate.getString("button.btnHuazuar"));
            btnEditoLibra121.setText(translate.getString("button.btnEditoLibra121"));
            btnEditoLibra1211.setText(translate.getString("button.btnEditoLibra1211"));
            btnShtoLibra.setText(translate.getString("button.btnShtoLibra"));
            btnEditoLibra121211.setText(translate.getString("button.btnEditoLibra121211"));
        }


        //manageusers column
        if(tablecol_id != null && tablecol_name != null && tablecol_email != null && tablecol_phone != null && tablecol_gender != null){
            tablecol_id.setText(translate.getString("column.tablecol_id"));
            tablecol_name.setText(translate.getString("column.tablecol_name"));
            tablecol_email.setText(translate.getString("column.tablecol_email"));
            tablecol_phone.setText(translate.getString("column.tablecol_phone"));
            tablecol_gender.setText(translate.getString("column.tablecol_gender"));
        }
        if(Paneli != null && shfaq != null && shfaqhuazuar != null && regjistroperdorues != null && filtro != null){
            Paneli.setText(translate.getString("text.Paneli"));
            shfaq.setText(translate.getString("button.shfaq"));
            shfaqhuazuar.setText(translate.getString("button.shfaqhuazuar"));
            regjistroperdorues.setText(translate.getString("button.regjistroperdorues"));
            filtro.setText(translate.getString("button.filtro"));
        }

        if(idTextFilter != null && emriTextFilter != null && emailTextFilter != null && telTextFilter != null && gjiniaTextFilter != null){
            idTextFilter.setText(translate.getString("text.idTextFilter"));
            emriTextFilter.setText(translate.getString("text.emriTextFilter"));
            emailTextFilter.setText(translate.getString("text.emailTextFilter"));
            telTextFilter.setText(translate.getString("text.telTextFilter"));
            gjiniaTextFilter.setText(translate.getString("text.gjiniaTextFilter"));
        }
    }
}









