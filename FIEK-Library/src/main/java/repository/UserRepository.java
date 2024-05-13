package repository;

import model.User;
import service.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository {
    public static User getByUsername (String username){
        String query = "SELECT * FROM USERS WHERE username = ? LIMIT 1";
        Connection connection = DBConnector.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1,username);
            ResultSet result = pst.executeQuery();
            if(result.next()){
                return getFromResultSet(result);
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    private static User getFromResultSet(ResultSet result){
        try {
            int id = result.getInt("id");
            String username = result.getString("username");
            String salt = result.getString("salt");
            String passwordHash = result.getString("passwordHash");
            return new User(id, username, salt, passwordHash);
        } catch (Exception e){
            return null;
        }
    }
}
