/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author adkm2
 */
public class Database {
    public static Connection getConnection () throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433; databaseName= LibraryManager; user =root; password =root;" + "encrypt = true; trustServerCertificate = true;";
        Connection connect = null;
        try  {
            DriverManager.getConnection(url);
            System.out.println("ket noi dc");
        } catch (SQLException e) {
            System.out.println("0");
        }
        return connect;
    }
    
    public static void closeConnection () {
        
    }
}
