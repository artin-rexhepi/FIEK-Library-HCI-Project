package controller;

import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.dto.BookDTO;

import java.net.URL;
import java.text.CollationElementIterator;
import java.util.Locale;
import java.util.ResourceBundle;

public class HuazuarBooksController implements Initializable {
    @FXML
    private TableView<BookDTO> tableViewHuazuar;

    @FXML
    private TableColumn<BookDTO, String> isbnColumn;

    @FXML
    private TableColumn<BookDTO, String> titleColumn;

    @FXML
    private TableColumn<BookDTO, String> authorColumn;

    @FXML
    private TableColumn<BookDTO, String> publisherColumn;

    @FXML
    private TableColumn<BookDTO, String> pubDateColumn;

    @FXML
    private TableColumn<BookDTO, String> borrowDateColumn;

    @FXML
    private TableColumn<BookDTO, String> dueDateColumn;

    @FXML
    private Button btnBack;

    @FXML
    private ImageView albanianFlag;

    @FXML
    private ImageView americanFlag;

    @FXML
    private Text Tabela;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set cell value factories for table columns
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        pubDateColumn.setCellValueFactory(new PropertyValueFactory<>("pubDate"));
        borrowDateColumn.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        // Set flag click event handlers
        albanianFlag.setOnMouseClicked(e -> translateAlbanian());
        americanFlag.setOnMouseClicked(e -> translateEnglish());

        // Vendos zmadhimin e kolonave
        tableViewHuazuar.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // gjeresia e kolonaveee
        isbnColumn.setPrefWidth(TableView.USE_COMPUTED_SIZE);
        titleColumn.setPrefWidth(TableView.USE_COMPUTED_SIZE);
        authorColumn.setPrefWidth(TableView.USE_COMPUTED_SIZE);
        publisherColumn.setPrefWidth(TableView.USE_COMPUTED_SIZE);
        pubDateColumn.setPrefWidth(TableView.USE_COMPUTED_SIZE);
        borrowDateColumn.setPrefWidth(TableView.USE_COMPUTED_SIZE);
        dueDateColumn.setPrefWidth(TableView.USE_COMPUTED_SIZE);
    }

    @FXML
    public void handleReturn() {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        Navigator.navigate(stage, Navigator.MANAGE_BOOKS_OPTIONS);
    }

    @FXML
    void translateEnglish() {
        Locale currentLocale = new Locale("en");
        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);

        Tabela.setText(translate.getString("text.Tabela"));
        isbnColumn.setText(translate.getString("column.isbn"));
        titleColumn.setText(translate.getString("column.title"));
        authorColumn.setText(translate.getString("column.author"));
        publisherColumn.setText(translate.getString("column.publisher"));
        pubDateColumn.setText(translate.getString("column.pubDate"));
        borrowDateColumn.setText(translate.getString("column.borrowDate"));
        dueDateColumn.setText(translate.getString("column.dueDate"));
    }

    @FXML
    void translateAlbanian() {
        Locale currentLocale = new Locale("sq");
        ResourceBundle translate = ResourceBundle.getBundle("translation.content", currentLocale);

        Tabela.setText(translate.getString("text.Tabela"));
        isbnColumn.setText(translate.getString("column.isbn"));
        titleColumn.setText(translate.getString("column.title"));
        authorColumn.setText(translate.getString("column.author"));
        publisherColumn.setText(translate.getString("column.publisher"));
        pubDateColumn.setText(translate.getString("column.pubDate"));
        borrowDateColumn.setText(translate.getString("column.borrowDate"));
        dueDateColumn.setText(translate.getString("column.dueDate"));
    }
}
