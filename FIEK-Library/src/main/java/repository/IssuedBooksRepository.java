package repository;

import model.dto.IssuedBookDto;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class IssuedBooksRepository {

    public boolean create(IssuedBookDto issuedBookData) {
        String query = """
                INSERT INTO issuedBooks (isbn, memberID, issueTime, renew_count)
                VALUES (?, ?, ?, ?)
                """;
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, issuedBookData.getIsbn());
            pst.setString(2, issuedBookData.getMemberID());
            pst.setTimestamp(3, issuedBookData.getIssueTime());
            pst.setInt(4, 14); // Set renew_count to 14
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<IssuedBookDto> getAllIssuedBooks() {
        List<IssuedBookDto> issuedBooks = new ArrayList<>();
        String query = "SELECT isbn, memberID, issueTime, renew_count FROM issuedBooks";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                IssuedBookDto issuedBook = new IssuedBookDto();
                issuedBook.setIsbn(rs.getString("isbn"));
                issuedBook.setMemberID(rs.getString("memberID"));
                issuedBook.setIssueTime(rs.getTimestamp("issueTime"));
                issuedBook.setRenewCount(rs.getInt("renew_count"));
                issuedBooks.add(issuedBook);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return issuedBooks;
    }

    public boolean decrementBookQuantity(String isbn) {
        String query = "UPDATE Book SET quantity = quantity - 1 WHERE isbn = ? AND quantity > 0";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, isbn);
            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean decrementRenewCount() {
        String query = "UPDATE issuedBooks SET renew_count = renew_count - 1 WHERE renew_count > 0";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
