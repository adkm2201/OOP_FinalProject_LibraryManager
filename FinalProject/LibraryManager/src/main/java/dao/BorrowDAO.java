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
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import model.BorrowedBook;

/**
 *
 * @author adkm2
 */
public class BorrowDAO {
    private Connection connection;
    private Database db = new Database();
    
    
    
    public List<BorrowedBook> getAllBorrowedBooks() throws SQLException {
        connection = db.connect();
        String query = "SELECT bb.BorrowID, u.Username, b.Title AS BookTitle, bb.BorrowDate, bb.ReturnDate " +
                       "FROM BorrowedBooks bb " +
                       "JOIN Users u ON bb.UserID = u.UserID " +
                       "JOIN Books b ON bb.BookID = b.BookID";
        List<BorrowedBook> borrowedBooks = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BorrowedBook borrowedBook = new BorrowedBook();
                borrowedBook.setBorrowID(rs.getInt("BorrowID"));
                borrowedBook.setUsername(rs.getString("Username"));
                borrowedBook.setBookTitle(rs.getString("BookTitle"));
                borrowedBook.setBorrowDate(rs.getDate("BorrowDate"));
                borrowedBook.setReturnDate(rs.getDate("ReturnDate"));
                borrowedBooks.add(borrowedBook);
            }
        } finally {
            connection.close();
        }
        return borrowedBooks;
        }
    
    public List<BorrowedBook> getBorrowedBooksByUserID(int userID) throws SQLException {
        connection = db.connect();
        String query = "SELECT bb.BorrowID, u.Username, b.Title AS BookTitle, bb.BorrowDate, bb.ReturnDate " +
                       "FROM BorrowedBooks bb " +
                       "JOIN Users u ON bb.UserID = u.UserID " +
                       "JOIN Books b ON bb.BookID = b.BookID " +
                       "WHERE bb.UserID = ?";
        List<BorrowedBook> borrowedBooks = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BorrowedBook borrowedBook = new BorrowedBook();
                borrowedBook.setBorrowID(rs.getInt("BorrowID"));
                borrowedBook.setUsername(rs.getString("Username"));
                borrowedBook.setBookTitle(rs.getString("BookTitle"));
                borrowedBook.setBorrowDate(rs.getDate("BorrowDate"));
                borrowedBook.setReturnDate(rs.getDate("ReturnDate"));
                borrowedBooks.add(borrowedBook);
            }
        } finally {
            connection.close();
        }
        return borrowedBooks;
    }
    
    public boolean addBorrowedBook(int userID, String title, java.sql.Date returnDate) throws Exception {
        // Lấy BookID từ tiêu đề sách
        connection = db.connect();
        String getBookIDQuery = "SELECT BookID FROM Books WHERE Title = ?";
        int bookID = -1;
        try (PreparedStatement stmt = connection.prepareStatement(getBookIDQuery)) {
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                bookID = rs.getInt("BookID");
            } else {
                throw new Exception("Book not found.");
            }
        }

        // Thêm dòng vào bảng BorrowedBooks
        String insertQuery = "INSERT INTO BorrowedBooks (UserID, BookID, BorrowDate, ReturnDate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(insertQuery)) {
            stmt.setInt(1, userID);
            stmt.setInt(2, bookID);
            stmt.setDate(3, new java.sql.Date(System.currentTimeMillis())); // Ngày mượn là hôm nay (YYYY-MM-DD)
            stmt.setDate(4, new Date(returnDate.getTime())); // Ngày trả (YYYY-MM-DD)
            return stmt.executeUpdate() > 0; // Trả về true nếu thêm thành công
        } finally {
            connection.close();
        }
    }
    
    public int countBorrowedByTitle(String title) throws SQLException {
        connection = db.connect();
        String query = "SELECT COUNT(*) AS BorrowCount FROM BorrowedBooks bb " +
                       "JOIN Books b ON bb.BookID = b.BookID " +
                       "WHERE b.Title = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("BorrowCount");
            }
        } finally {
            connection.close();
        }
        return 0; // Trả về 0 nếu không có dòng nào
    }
    
    public boolean updateAvailableByTitle(String title, int borrowedCount) throws SQLException {
        connection = db.connect();
        String query = "UPDATE Books SET Available = GREATEST(Available - ?, 0) WHERE Title = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, borrowedCount); // Số lượng sách đã mượn
            stmt.setString(2, title);
            return stmt.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
        } finally {
            connection.close();
        }
    }
    
    public int getAvailableByTitle(String title) throws SQLException {
        connection = db.connect();
        String query = "SELECT Available FROM Books WHERE Title = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("Available");
            }
        } connection.close();
        throw new SQLException("Book not found!");
    }
    
}
