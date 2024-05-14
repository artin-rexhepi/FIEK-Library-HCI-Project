package repository;

import javafx.fxml.FXML;
import service.DBConnector;

import java.sql.*;

public class AdminRepository {
    public int getLoggedInUserId() {
        return 1;
    }
    public String fetchUserNameFromDatabase(int userId) {
        String userName = "";
        String sql = "SELECT person_name FROM Member WHERE id = ?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    userName = rs.getString("person_name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userName;
    }
}
