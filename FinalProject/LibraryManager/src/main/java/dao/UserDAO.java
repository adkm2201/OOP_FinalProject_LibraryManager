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

    public boolean addUser(User user) throws SQLException {
        connection = db.connect();
        String query = "INSERT INTO Users (Username, password, UserType) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getUserType());
            stmt.executeUpdate();
            return true;
        } catch (SQLException exception) {
            return false;
        } finally {
            connection.close();
        }
        
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
    
}