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
                INSERT INTO Book (ISBN, title, subject, publisher, publicationDate, language, numberOfPages, author_id)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;
        try{
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, bookData.getISBN());
            pst.setString(2, bookData.getTitle());
            pst.setString(3, bookData.getSubject());
            pst.setString(4, bookData.getPublisher());
            pst.setDate(5, java.sql.Date.valueOf(bookData.getPublicationDate()));
            pst.setString(6, bookData.getLanguage());
            pst.setInt(7, bookData.getNumberOfPages());
            pst.setInt(8, bookData.getAuthorId());
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

    private static Book getFromResultSet(ResultSet result){
        try{
            String ISBN = result.getString("ISBN");
            String title = result.getString("title");
            String subject = result.getString("subject");
            String publisher = result.getString("publisher");
            LocalDate publicationDate = result.getDate("publicationDate").toLocalDate();
            String language = result.getString("language");
            int numberOfPages = result.getInt("numberOfPages");
            int authorId = result.getInt("author_id");
            return new Book(ISBN, title, subject, publisher, publicationDate, language, numberOfPages, authorId);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
