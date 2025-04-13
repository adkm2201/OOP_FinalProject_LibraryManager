package controller;

import dao.BookDAO;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Book;
import view.LibrarianForm;

/**
 *
 * @author adkm2
 */
public class LibrarianController {
    private BookDAO bookDAO = new BookDAO();

    public LibrarianController() {
        bookDAO = new BookDAO();
    }
    
    public void loadLibrarianForm() {
        LibrarianForm libForm = new LibrarianForm();
        libForm.setVisible(true);
    }
    
    public void addBook(String title, String author, String genre, String isbn, int available, boolean bookType, int numberOfPages, String fileFormat) {
        try {
            Book book = new Book(title, author, genre, isbn, available, bookType, numberOfPages, fileFormat);
            Book addedBook = bookDAO.add(book); // Thêm sách và lấy đối tượng Book với BookID

            if (addedBook.getBookID() > 0) {
                JOptionPane.showMessageDialog(null, "Book added successfully! Book ID: " + addedBook.getBookID());
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add book.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void loadBooksToTable(JTable table) {
        try {
            List<Book> books = bookDAO.getAll();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Clear existing rows
            for (Book book : books) {
                model.addRow(new Object[]{
                        book.getBookID(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getGenre(),
                        book.getIsbn(),
                        book.getAvailable(),
                        book.getNumberOfPages(),
                        book.getFileFormat()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void loadBooksByTypeToTable(JTable table, boolean bookType) {
        try {
            List<Book> books = bookDAO.getBooksByType(bookType); // Lấy sách theo BookType từ BookDAO
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ trong bảng
            for (Book book : books) {
                model.addRow(new Object[]{
                        book.getBookID(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getGenre(),
                        book.getIsbn(),
                        book.getAvailable(),
                        book.getNumberOfPages(),
                        book.getFileFormat()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void deleteBook(int bookID) {
        try {
            if (bookDAO.delete(bookID)) {
                JOptionPane.showMessageDialog(null, "Book deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete book. Book ID not found.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Can't delete because this book was borrowed.");
        }
    }
    
    public void updateBook(int bookID, String title, String author, String genre, String isbn, int available, boolean bookType, int numberOfPages, String fileFormat) {
        try {
            Book book = new Book();
            book.setBookID(bookID);
            book.setTitle(title);
            book.setAuthor(author);
            book.setGenre(genre);
            book.setIsbn(isbn);
            book.setAvailable(available);
            book.setBookType(bookType);
            book.setNumberOfPages(numberOfPages);
            book.setFileFormat(fileFormat);

            if (bookDAO.update(book)) {
                JOptionPane.showMessageDialog(null, "Book updated successfully!");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update book. Book ID not found.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public Book getBookByID(int bookID) {
        try {
            return bookDAO.getBookByID(bookID); // Gọi phương thức từ BookDAO
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return null;
        }
    }
    
    public List<Book> getBooksByFilter(String title, String author, List<String> genres) {
        try {
            return bookDAO.getBooksByFilter(title, author, genres);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
}
