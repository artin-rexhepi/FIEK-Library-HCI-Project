package repository;

import model.dto.MemberDto;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository {

    public boolean create(MemberDto memberData) {
        Connection conn = DBConnector.getConnection();
        String query = """
                INSERT INTO Member (memberid, name, email, phone, gender)
                VALUES (?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, memberData.getIDstudendore());
            pst.setString(2, memberData.getEmri());
            pst.setString(3, memberData.getEmail());
            pst.setString(4, memberData.getNumerTelefoni());
            pst.setString(5, memberData.getGjinia());
            pst.executeUpdate();
            pst.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getAllMemberIds() {
        List<String> memberIds = new ArrayList<>();
        Connection conn = DBConnector.getConnection();
        String query = "SELECT memberid FROM Member";
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                memberIds.add(rs.getString("memberid"));
            }
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return memberIds;
    }
}
