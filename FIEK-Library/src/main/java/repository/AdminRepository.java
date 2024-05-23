package repository;

import controller.AdminPageController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Member;
import model.dto.MemberDto;
import model.filter.MemberFilter;
import service.DBConnector;

import java.sql.*;
import java.util.List;

public class AdminRepository {

    public static void setMembers(TableColumn<MemberDto, String> column, String columnName) {
        if (column == null) {
            System.err.println("TableColumn is null for column: " + columnName);
            return;
        }
        switch (columnName) {
            case "IDstudendore":
                column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getIDstudendore()));
                break;
            case "emri":
                column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmri()));
                break;
            case "email":
                column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
                break;
            case "numerTelefoni":
                column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNumerTelefoni()));
                break;
            case "gjinia":
                column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGjinia()));
                break;
            default:
                System.err.println("Unknown column: " + columnName);
                break;
        }
    }
    public static ObservableList<MemberDto> getMemberData(){
        ObservableList<MemberDto> list = FXCollections.observableArrayList();
        try(Connection connection = DBConnector.getConnection();){
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Member;");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                list.add(new MemberDto(rs.getString("memberid"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("gender")
                ));
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return list;
    }

    public static ObservableList<MemberDto> getIssuedBookMember(){
        ObservableList<MemberDto> list = FXCollections.observableArrayList();
        try(Connection connection = DBConnector.getConnection();){
            PreparedStatement ps = connection.prepareStatement("SELECT m.memberid, m.name, m.email, m.phone, m.gender FROM Member m JOIN issuedBooks ib ON m.memberid = ib.memberID;");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                list.add(new MemberDto(rs.getString("memberid"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("gender")
                ));
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return list;
    }
    public static ObservableList<MemberDto> getByFilter(MemberFilter memberFilter){
        ObservableList<MemberDto> members = FXCollections.observableArrayList();

        String query = "SELECT * FROM Member WHERE 1 = 1";
        String filterQuery = memberFilter.buildQuery();
        query += filterQuery;
        try(Connection connection = DBConnector.getConnection();){
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                members.add(new MemberDto(rs.getString("memberid"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("gender")
                ));
            }
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
        return members;
    }
}
