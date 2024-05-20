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
import service.DBConnector;

import java.sql.*;
import java.util.List;

public class AdminRepository {
//    public String username;
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getUsername() {
//        return this.username;
//    }

//    public String fetchUserNameFromDatabase() {
//        String userName = "";
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        String query = "SELECT username FROM Users WHERE username = ?";
//        try {
//            con = DBConnector.getConnection();
//            ps = con.prepareStatement(query);
//            ps.setString(1, getUsername());
//            rs = ps.executeQuery();
//            if (rs.next()) {
//                userName = rs.getString("username");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//
//            try {
//                if (rs != null) rs.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                if (ps != null) ps.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            try {
//                if (con != null) con.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return userName;
//    }


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

}
