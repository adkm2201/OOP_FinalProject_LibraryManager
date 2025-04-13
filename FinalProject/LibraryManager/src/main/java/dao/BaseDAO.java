package dao;

import database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO<T> {
    protected Connection connection;
    protected Database db = new Database();

    public BaseDAO() {
        this.connection = null;
    }

    public BaseDAO(Connection connection) {
        this.connection = connection;
    }

    // Abstract methods for CRUD operations
    public abstract T add(T obj) throws SQLException;
    public abstract boolean update(T obj) throws SQLException;
    public abstract boolean delete(int id) throws SQLException;
    public abstract List<T> getAll() throws SQLException;

    // Common method to close the connection
    protected void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    // Common method to execute an update query
    protected boolean executeUpdate(String query, Object... params) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            return stmt.executeUpdate() > 0;
        }
    }
}