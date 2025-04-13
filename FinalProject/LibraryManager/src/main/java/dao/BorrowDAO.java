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
import java.time.LocalDate;
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
                       "WHERE b.Title = ? AND bb.ReturnDate > CURRENT_DATE";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("BorrowCount"); // Trả về số dòng đếm được
            }
        } finally {
            connection.close();
        }
        return 0; // Trả về 0 nếu không có dòng nào
    }
    
//    public boolean updateAvailableByTitle(String title, int borrowedCount) throws SQLException {
//        connection = db.connect();
//        String query = "UPDATE Books SET Available = GREATEST(Available - 1, 0) WHERE Title = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(query)) {
//            stmt.setString(1, title);
//            return stmt.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
//        } finally {
//            connection.close();
//        }
//    }
    
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
    
    public int getBookIDByTitle(String title) throws SQLException {
        connection = db.connect();
        String query = "SELECT BookID FROM Books WHERE Title = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("BookID");
            }
        } finally {
            connection.close();
        }
        return -1; // Trả về -1 nếu không tìm thấy
    }
    
    public java.sql.Date getReturnDate(int userID, int bookID) throws SQLException {
        connection = db.connect();
        String query = "SELECT ReturnDate FROM BorrowedBooks WHERE UserID = ? AND BookID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setInt(2, bookID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDate("ReturnDate");
            }
        } finally {
            connection.close();
        }
        return null; // Trả về null nếu không tìm thấy
    }
    
    public boolean updateReturnDate(int userID, int bookID, java.sql.Date returnDate) throws SQLException {
        connection = db.connect();
        String query = "UPDATE BorrowedBooks SET ReturnDate = ? WHERE UserID = ? AND BookID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, returnDate);
            stmt.setInt(2, userID);
            stmt.setInt(3, bookID);
            return stmt.executeUpdate() > 0;
        } finally {
            connection.close();
        }
    }
    
    public boolean incrementBookAvailability(int bookID) throws SQLException {
        connection = db.connect();
        String query = "UPDATE Books SET Available = Available + 1 WHERE BookID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookID);
            return stmt.executeUpdate() > 0;
        } finally {
            connection.close();
        }
    }
    
    public boolean isBookAlreadyBorrowed(int userID, int bookID) throws SQLException {
        connection = db.connect();
        String query = "SELECT ReturnDate FROM BorrowedBooks WHERE UserID = ? AND BookID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setInt(2, bookID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                java.sql.Date sqlReturnDate = rs.getDate("ReturnDate");
                if (sqlReturnDate != null) {
                    // Chuyển đổi ReturnDate từ java.sql.Date sang LocalDate
                    LocalDate returnDate = sqlReturnDate.toLocalDate();
                    LocalDate today = LocalDate.now();

                    // So sánh returnDate với today
                    return returnDate.isAfter(today); // Trả về true nếu returnDate > today
                }
            }
        } finally {
            connection.close();
        }
        return false; // Trả về false nếu không tồn tại hoặc returnDate <= today
    }
    
    public int countReturnedBooksByDate(int userID, int bookID) throws SQLException {
        connection = db.connect();
        String query = "SELECT COUNT(*) AS Count FROM BorrowedBooks WHERE UserID = ? AND BookID = ? AND ReturnDate <= ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setInt(2, bookID);
            stmt.setDate(3, java.sql.Date.valueOf(LocalDate.now())); // Lấy ngày hiện tại
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("Count"); // Trả về số dòng đếm được
            }
        } finally {
            connection.close();
        }
        return 0; // Trả về 0 nếu không có dòng nào
    }
    
    public int countReturnedBook(int userID, int bookID) throws SQLException {
        connection = db.connect();
        String query = "SELECT COUNT(*) AS Count FROM BorrowedBooks WHERE UserID = ? AND BookID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userID);
            stmt.setInt(2, bookID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("Count"); // Trả về số dòng đếm được
            }
        } finally {
            connection.close();
        }
        return 0; // Trả về 0 nếu không có dòng nào    
    }
    
    public boolean decrementBookAvailability(int bookID) throws SQLException {
        connection = db.connect();
        String query = "UPDATE Books SET Available = Available - 1 WHERE BookID = ? AND Available > 0";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookID);
            return stmt.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
        } finally {
            connection.close();
        }
    }

    public int getAvailableByBookID(int bookID) throws SQLException {
        connection = db.connect();
        String query = "SELECT Available FROM Books WHERE BookID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("Available");
            }
        } finally {
            connection.close();
        }
        return 0; // Trả về 0 nếu không tìm thấy
        } 
    
}
