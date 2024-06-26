package repository;

import model.Book;
import model.dto.BookDTO;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRepository {

    public boolean create(BookDTO bookData) {
        String query = """
        INSERT INTO Book (ISBN, title, author, publisher, genre, quantity, isAvailable)
        VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, bookData.getISBN());
            pst.setString(2, bookData.getTitle());
            pst.setString(3, bookData.getAuthor());
            pst.setString(4, bookData.getPublisher());
            pst.setString(5, bookData.getSubject());
            pst.setInt(6, Integer.parseInt(bookData.getQuantity()));
            pst.setBoolean(7, true);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(BookDTO bookData) {
        String query = """
        DELETE FROM Book 
        WHERE ISBN = ? AND title = ? AND author = ? AND publisher = ? AND genre = ?
        """;

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, bookData.getISBN());
            pst.setString(2, bookData.getTitle());
            pst.setString(3, bookData.getAuthor());
            pst.setString(4, bookData.getPublisher());
            pst.setString(5, bookData.getSubject());
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Book getByISBN(String ISBN) {
        String query = "SELECT * FROM Book WHERE ISBN = ? LIMIT 1";
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, ISBN);
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                return getFromResultSet(result);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Book getFromResultSet(ResultSet result) {
        try {
            String ISBN = result.getString("isbn");
            String title = result.getString("title");
            String author = result.getString("author");
            String publisher = result.getString("publisher");
            String genre = result.getString("genre");
            int quantity = result.getInt("quantity");
            boolean isAvailable = result.getBoolean("isAvailable");
            return new Book(ISBN, title, genre, publisher, String.valueOf(quantity), author, isAvailable);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public int getBookQuantity(String isbn) {
        String query = "SELECT quantity FROM Book WHERE ISBN = ?";
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, isbn);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("quantity");
                } else {
                    // No book found with the given ISBN
                    return 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
