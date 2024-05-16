package repository;

import controller.AdminPageController;
import javafx.fxml.FXML;
import service.DBConnector;

import java.sql.*;

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
}
