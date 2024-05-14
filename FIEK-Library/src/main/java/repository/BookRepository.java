package repository;

import model.Book;
import model.dto.BookDTO;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class BookRepository {

    public static boolean create(BookDTO bookData){
        Connection conn = DBConnector.getConnection();
        String query = """
        INSERT INTO Book (ISBN, title, author, publisher, genre, quantity, isAvailable)
        VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        try{
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, bookData.getISBN());
            pst.setString(2, bookData.getTitle());
            pst.setString(3, bookData.getAuthor());
            pst.setString(4, bookData.getPublisher());
            pst.setString(5, bookData.getSubject());
            pst.setInt(6, Integer.parseInt(bookData.getQuantity())); // Parse quantity to int
            pst.setBoolean(7, true); // Assuming isAvailable should always be true when adding a new book
            pst.executeUpdate();
            pst.close();
            conn.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static Book getByISBN(String ISBN){
        String query = "SELECT * FROM Book WHERE ISBN = ? LIMIT 1";
        Connection connection = DBConnector.getConnection();
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, ISBN);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return getFromResultSet(result);
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    private static Book getFromResultSet(ResultSet result) {
        try {
            String ISBN = result.getString("isbn");
            String title = result.getString("title");
            String author = result.getString("author");
            String publisher = result.getString("publisher");
            String genre = result.getString("genre");
            int quantity = result.getInt("quantity");
            // Assuming isAvailable is boolean, retrieve as boolean or convert if necessary
            boolean isAvailable = result.getBoolean("isAvailable");

            // Return a new Book object with retrieved data
            return new Book(ISBN, title, null, publisher, String.valueOf(quantity), author);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    }

