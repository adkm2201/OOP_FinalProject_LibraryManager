package dao;

import database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import model.Book;

/**
 *
 * @author adkm2
 */
public class BookDAO extends BaseDAO<Book>{
    private Connection connection;
    private Database db = new Database();
    
    public BookDAO() {
        super();
    }

    public BookDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Book add(Book book) throws SQLException {
        connection = db.connect();
        String query = "INSERT INTO Books (Title, Author, Genre, ISBN, Available, BookType, NumberOfPages, FileFormat) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getGenre());
            stmt.setString(4, book.getIsbn());
            stmt.setInt(5, book.getAvailable());
            stmt.setBoolean(6, book.isBookType());
            stmt.setInt(7, book.getNumberOfPages());
            stmt.setString(8, book.getFileFormat());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                // Lấy BookID được tự động tạo
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        book.setBookID(generatedKeys.getInt(1)); // Gán BookID vào đối tượng Book
                    }
                }
            }
            return book; // Trả về đối tượng Book với BookID đã được gán
        } finally {
            connection.close();
            } 
    }
    
    @Override
    public boolean update(Book book) throws SQLException {
        connection = db.connect();
        String query = "UPDATE Books SET Title = ?, Author = ?, Genre = ?, ISBN = ?, Available = ?, BookType = ?, NumberOfPages = ?, FileFormat = ? WHERE BookID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getGenre());
            stmt.setString(4, book.getIsbn());
            stmt.setInt(5, book.getAvailable());
            stmt.setBoolean(6, book.isBookType());
            stmt.setInt(7, book.getNumberOfPages());
            stmt.setString(8, book.getFileFormat());
            stmt.setInt(9, book.getBookID());
            return stmt.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
        } finally {
            connection.close();
        }
    }

    @Override
    public boolean delete(int bookID) throws SQLException {
        connection = db.connect();
        String query = "DELETE FROM Books WHERE BookID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookID);
            return stmt.executeUpdate() > 0; // Trả về true nếu xóa thành công
        } finally {
            connection.close();
        }
    }

    @Override
    public List<Book> getAll() throws SQLException {
        connection = db.connect();
        String query = "SELECT * FROM Books";
        List<Book> books = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Book book = new Book();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setAuthor(rs.getString("Author"));
                book.setGenre(rs.getString("Genre"));
                book.setIsbn(rs.getString("ISBN"));
                book.setAvailable(rs.getInt("Available"));
                book.setBookType(rs.getBoolean("BookType"));
                book.setNumberOfPages(rs.getInt("NumberOfPages"));
                book.setFileFormat(rs.getString("FileFormat"));
                books.add(book);
            } 
        } finally {
            connection.close();
        }
        return books;
    }
    
    public List<Book> getBooksByType(boolean bookType) throws SQLException {
        connection = db.connect();
        String query = "SELECT * FROM Books WHERE BookType = ?";
        List<Book> books = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setBoolean(1, bookType);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setAuthor(rs.getString("Author"));
                book.setGenre(rs.getString("Genre"));
                book.setIsbn(rs.getString("ISBN"));
                book.setAvailable(rs.getInt("Available"));
                book.setBookType(rs.getBoolean("BookType"));
                book.setNumberOfPages(rs.getInt("NumberOfPages"));
                book.setFileFormat(rs.getString("FileFormat"));
                books.add(book);
            }
        } finally {
            connection.close();
            }
        return books;
    }
       
    public Book getBookByID(int bookID) throws SQLException {
        connection = db.connect();
        String query = "SELECT * FROM Books WHERE BookID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, bookID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Book book = new Book();
                book.setTitle(rs.getString("Title"));
                book.setAuthor(rs.getString("Author"));
                book.setGenre(rs.getString("Genre"));
                book.setIsbn(rs.getString("ISBN"));
                book.setAvailable(rs.getInt("Available"));
                book.setBookType(rs.getBoolean("BookType"));
                book.setNumberOfPages(rs.getInt("NumberOfPages"));
                book.setFileFormat(rs.getString("FileFormat"));
                return book; // Trả về đối tượng Book
            }
        } finally {
            connection.close();
        }
        return null; // Trả về null nếu không tìm thấy sách
    }
    
    public List<Book> getBooksByFilter(String title, String author, List<String> genres) throws SQLException {
        connection = db.connect();
        
        StringBuilder query = new StringBuilder("SELECT * FROM Books WHERE 1=1");

        // Thêm điều kiện lọc theo title nếu có
        if (title != null && !title.trim().isEmpty()) {
            query.append(" AND Title LIKE ?");
        }

        // Thêm điều kiện lọc theo author nếu có
        if (author != null && !author.trim().isEmpty()) {
            query.append(" AND Author LIKE ?");
        }

        // Thêm điều kiện lọc theo genre nếu có
        if (genres != null && !genres.isEmpty()) {
            query.append(" AND Genre IN (");
            for (int i = 0; i < genres.size(); i++) {
                query.append("?");
                if (i < genres.size() - 1) {
                    query.append(", ");
                }
            }
            query.append(")");
        }

        List<Book> books = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query.toString())) {
            int paramIndex = 1;

            // Gán giá trị cho title
            if (title != null && !title.trim().isEmpty()) {
                stmt.setString(paramIndex++, "%" + title + "%");
            }

            // Gán giá trị cho author
            if (author != null && !author.trim().isEmpty()) {
                stmt.setString(paramIndex++, "%" + author + "%");
            }

            // Gán giá trị cho genres
            if (genres != null && !genres.isEmpty()) {
                for (String genre : genres) {
                    stmt.setString(paramIndex++, genre);
                }
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookID(rs.getInt("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setAuthor(rs.getString("Author"));
                book.setGenre(rs.getString("Genre"));
                book.setIsbn(rs.getString("ISBN"));
                book.setAvailable(rs.getInt("Available"));
                book.setBookType(rs.getBoolean("BookType"));
                book.setNumberOfPages(rs.getInt("NumberOfPages"));
                book.setFileFormat(rs.getString("FileFormat"));
                books.add(book);
            }
        } finally {
            connection.close();
        }
        return books;
    }

}
