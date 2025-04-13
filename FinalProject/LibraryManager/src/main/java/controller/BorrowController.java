/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.BorrowDAO;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.BorrowedBook;

/**
 *
 * @author adkm2
 */
public class BorrowController {
    private BorrowDAO borrowDAO = new BorrowDAO();
    
    
    public void loadBorrowedBooksToTable(JTable table) {
        try {
            List<BorrowedBook> borrowedBooks = borrowDAO.getAllBorrowedBooks();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ
            for (BorrowedBook book : borrowedBooks) {
                model.addRow(new Object[]{
                    book.getBorrowID(),
                    book.getUsername(),
                    book.getBookTitle(),
                    book.getBorrowDate(),
                    book.getReturnDate()
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadBorrowedBooksToTable(JTable table, int userID) {
        try {
            List<BorrowedBook> borrowedBooks = borrowDAO.getBorrowedBooksByUserID(userID); // Lấy sách theo userID
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ
            for (BorrowedBook book : borrowedBooks) {
                model.addRow(new Object[]{
                        book.getBorrowID(),
                        book.getUsername(),
                        book.getBookTitle(),
                        book.getBorrowDate(),
                        book.getReturnDate()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public boolean borrowBook(int userID, String title, java.sql.Date returnDate) {
        try {
            // Thêm dòng vào bảng BorrowedBooks
            boolean isBorrowed = borrowDAO.addBorrowedBook(userID, title, returnDate);
            if (isBorrowed) {
                // Đếm số dòng của title trong bảng BorrowedBooks
                int borrowedCount = borrowDAO.countBorrowedByTitle(title);
                // Cập nhật cột Available trong bảng Books
                borrowDAO.updateAvailableByTitle(title, borrowedCount);

                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int getAvailableByTitle(String title) {
        try {
            return borrowDAO.getAvailableByTitle(title);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return -1; // Trả về -1 nếu có lỗi
        }
    }
    
}
