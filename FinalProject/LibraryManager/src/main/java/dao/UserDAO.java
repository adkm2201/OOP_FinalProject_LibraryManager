/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author adkm2
 */
public class UserDAO {
    private Connection connection;
    private Database db = new Database();

    public UserDAO() {
    }
    
    public UserDAO(Connection connection) {
        this.connection = connection;
    }
    
    public User getUserByUsername(String username) throws SQLException {
        connection = db.connect();
        String query = "SELECT * FROM Users WHERE Username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                    //rs.getInt("UserID"),
                    rs.getString("Username"),
                    rs.getString("password"),
                    rs.getInt("UserType")
                );
            }
        }
        connection.close();
        return null;
    }
    
    public User getUserByID(int userID) throws SQLException {
    connection = db.connect();
    String query = "SELECT Username, password, UserType FROM Users WHERE UserID = ?";
    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setInt(1, userID); // Sử dụng userID để tìm kiếm
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new User(
                rs.getString("Username"),
                rs.getString("password"),
                rs.getInt("UserType")
            );
        }
    } finally {
        connection.close();
    }
    return null; // Trả về null nếu không tìm thấy người dùng
}

    public User addUser(User user) throws SQLException {
        connection = db.connect();
        String query = "INSERT INTO Users (Username, password, UserType) OUTPUT INSERTED.UserID VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getUserType());

            // Execute the query and get the generated UserID
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int generatedUserId = rs.getInt("UserID");
                user.setUserId(generatedUserId); // Set the generated UserID to the User object
                return user; // Return the updated User object
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw exception; // Re-throw the exception for the caller to handle
        } finally {
            if (connection != null) {
                connection.close(); // Ensure the connection is closed in the finally block
            }
        }
        return null; // Return null if the insertion fails
    }
    
    public int getUserIDByUsername(String username) throws SQLException {
        String query = "SELECT UserID FROM Users WHERE Username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("UserID");
            }
        }
        throw new SQLException("User not found!");
    }
    
    public boolean isExistUser(String username) throws SQLException {
        connection = db.connect();       
        String query = "SELECT * FROM Users WHERE Username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
           connection.close();
        }
        return false;
    }
    
    public boolean updateUser(User user) throws SQLException {
        connection = db.connect();
        String query = "UPDATE Users SET Username = ?, password = ?, UserType = ? WHERE UserID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getUserType());
            stmt.setInt(4, user.getUserId());
            return stmt.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
        } finally {
           connection.close();
        }
    }

    public boolean deleteUser(int userId) throws SQLException {
        connection = db.connect();
        String query = "DELETE FROM Users WHERE UserID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            return stmt.executeUpdate() > 0;
        } finally {
           connection.close();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        connection = db.connect();
        String query = "SELECT * FROM Users";
        List<User> users = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("UserID")); // Set UserID from database
                user.setUsername(rs.getString("Username"));
                user.setPassword(rs.getString("password"));
                user.setUserType(rs.getInt("UserType"));
                users.add(user); // Add the user to the list
                
            }
        } finally {
           connection.close();
        }
        return users;
    }
    
    public int getUserTypeByUsername(String username) throws SQLException {
        String query = "SELECT UserType FROM Users WHERE Username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("UserType"); // Trả về UserType
            }
        }
        throw new SQLException("User not found!"); // Ném lỗi nếu không tìm thấy người dùng
    }
    
}