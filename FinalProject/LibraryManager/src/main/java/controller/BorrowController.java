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
            return false;
        }
    }
    
    public int getAvailableByTitle(String title) {
        try {
            return borrowDAO.getAvailableByTitle(title);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return -1; // Trả về -1 nếu có lỗi
        }
    }
    
    public int getBookIDByTitle(String title) {
        try {
            return borrowDAO.getBookIDByTitle(title);
        } catch (SQLException e) {
            return -1; // Trả về -1 nếu không tìm thấy
        }
    }
    
    public java.sql.Date getReturnDate(int userID, int bookID) {
        try {
            return borrowDAO.getReturnDate(userID, bookID);
        } catch (SQLException e) {
            return null;
        }
    }
    
    public boolean updateReturnDate(int userID, int bookID, java.sql.Date returnDate) {
        try {
            return borrowDAO.updateReturnDate(userID, bookID, returnDate);
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean incrementBookAvailability(int bookID) {
        try {
            return borrowDAO.incrementBookAvailability(bookID);
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean isBookAlreadyBorrowed(int userID, int bookID) {
        try {
            return borrowDAO.isBookAlreadyBorrowed(userID, bookID);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public int countReturnedBooksByDate(int userID, int bookID) {
        try {
            return borrowDAO.countReturnedBooksByDate(userID, bookID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int CountReturnedBooks(int userID, int bookID) {
        try {
            return borrowDAO.countReturnedBook(userID, bookID);
        } catch (SQLException ex) {
            Logger.getLogger(BorrowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}
