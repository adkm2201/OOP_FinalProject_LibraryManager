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
import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.AdminForm;
import view.LibrarianForm;
import view.LoginForm;
import view.ReaderForm;

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
        if (userDAO.addUser(user) != null) return true;
        return false;
    }
    
    public void loginSuccess(String username) {
        try {
            // Lấy userType từ cơ sở dữ liệu dựa trên username
            int userType = userDAO.getUserTypeByUsername(username);

            // Đóng LoginForm
            loginForm.dispose();

            // Mở form tương ứng dựa trên userType
            switch (userType) {
                case 0: // Reader
                    new ReaderForm(this.getLoginForm()).setVisible(true);
                    break;
                case 1: // Librarian
                    new LibrarianForm().setVisible(true);
                    break;
                case 2: // Admin
                    new AdminForm().setVisible(true);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid user type!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public int getUserIDByUsername(String username) {
        try {
            return userDAO.getUserIDByUsername(username); // Gọi phương thức từ UserDAO
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return -1; // Trả về -1 nếu có lỗi
        }
    }
    
}