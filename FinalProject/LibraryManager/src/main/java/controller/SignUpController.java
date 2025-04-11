/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author adkm2
 */
import dao.UserDAO;
import database.Database;
import model.User;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SignUpController {
    private UserDAO userDAO = new UserDAO();

    public SignUpController() {
        Connection connection = Database.connect();
        this.userDAO = new UserDAO(connection);
    }

    public void signUp(String username, String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            User user = new User(username, password, 0); // Default UserType = 0 (reader)
            userDAO.addUser(user);
            JOptionPane.showMessageDialog(null, "Sign up successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while signing up.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
