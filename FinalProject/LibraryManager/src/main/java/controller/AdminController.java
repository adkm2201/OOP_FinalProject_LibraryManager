package controller;

import dao.UserDAO;
import java.sql.SQLException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.User;

import view.AdminForm;

/**
 *
 * @author adkm2
 */
public class AdminController {
    private UserDAO userDAO = new UserDAO();

    public AdminController() {
        userDAO = new UserDAO();
    }
    
    public void loadAdminForm() {
        AdminForm adminForm = new AdminForm();
        adminForm.setVisible(true);
    }
    
    public void addUser(String username, String password, int userType) {
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setUserType(userType);

            User addedUser = userDAO.add(user); // Add user and get the generated UserID
            if (addedUser != null) {
                JOptionPane.showMessageDialog(null, "User added successfully! UserID: " + addedUser.getUserId());
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add user.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: User ID or Username existed");
        }
    }

    public void updateUser(int userId, String username, String password, int userType) {
        try {
            User user = new User();
            user.setUserId(userId);
            user.setUsername(username);
            user.setPassword(password);
            user.setUserType(userType);

            if (userDAO.update(user)) {
                JOptionPane.showMessageDialog(null, "User updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update user.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "UserType is unvalid");
        }
}

    public void deleteUser(int userId) {
        try {
            if (userDAO.delete(userId)) {
                JOptionPane.showMessageDialog(null, "User deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete user.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadUsersToTable(JTable table) {
        try {
            List<User> users = userDAO.getAll();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Clear existing rows
            for (User user : users) {
                model.addRow(new Object[]{
                        user.getUserId(),
                        user.getUsername(),
                        user.getPassword(),
                        user.getUserTypeAsString() // Use the string representation
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

public User getUserById(int userId) {
    try {
        return userDAO.getUserByID(userId); // Gọi DAO để lấy thông tin người dùng
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: Can't get User Infomation");
        return null;
    }
}

}
