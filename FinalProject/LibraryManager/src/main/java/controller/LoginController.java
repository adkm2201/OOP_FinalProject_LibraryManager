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
import model.User;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.LoginForm;

public class LoginController {
    private UserDAO userDAO = new UserDAO();
    private LoginForm loginForm;

    public LoginController() {
    }
    
//    public LoginController(Connection connection) {
//        this.userDAO = new UserDAO(connection);
//    }

    public boolean login(String username, String password) {
        try {
            User user = userDAO.getUserByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(null, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error occurred while connecting to the database.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        //JOptionPane.showMessageDialog(null, "Invalid username or password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
        return false;
    }
    
    public boolean checkUser(String username) throws SQLException {
        return userDAO.isExistUser(username);
    }
    
    public void setLoginForm(LoginForm loginForm) {
        this.loginForm = loginForm;
    }
    
    public LoginForm getLoginForm() {
        return loginForm;
    }

    public void LoadLoginForm() {
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
    }
    
    public boolean register(String username, String password, int userType) throws SQLException {
        User user = new User(username, password, 0);
        return userDAO.addUser(user);
    }
    
}