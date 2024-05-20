package controller;


import app.Navigator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.dto.BookDTO;
import model.dto.BookDTO;

import java.net.URL;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        pubDateColumn.setCellValueFactory(new PropertyValueFactory<>("pubDate"));
        borrowDateColumn.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        // Vendosja e gjerësisë së kolonave për të mbushur tabelën në mënyrë të barabartë
        double totalWidth = tableViewHuazuar.getPrefWidth();
        double columnWidth = totalWidth / tableViewHuazuar.getColumns().size();

        isbnColumn.setPrefWidth(columnWidth);
        titleColumn.setPrefWidth(columnWidth);
        authorColumn.setPrefWidth(columnWidth);
        publisherColumn.setPrefWidth(columnWidth);
        pubDateColumn.setPrefWidth(columnWidth);
        borrowDateColumn.setPrefWidth(columnWidth);
        dueDateColumn.setPrefWidth(columnWidth);
    }

    @FXML
    public void handleReturn() {
        Stage stage = (Stage) btnBack.getScene().getWindow();
        // Kthehu në panelin e menaxhimit të përdoruesve
        Navigator.navigate(stage, Navigator.MANAGE_BOOKS_OPTIONS);
    }
}










