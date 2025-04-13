/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;



/**
 *
 * @author adkm2
 */
public class Database {
    public static Connection connect () {
        String url = "jdbc:sqlserver://localhost:1433; databaseName= LibraryManager; user =root; password =root;" + "encrypt = true; trustServerCertificate = true;";
        Connection connect = null;
        try  {
            connect = DriverManager.getConnection(url);
            System.out.println("ket noi dc");
        } catch (SQLException e) {
            System.out.println("0");
        }
        return connect;
    }
    
    public static void closeConnection (Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("dong ket noi dc");
            } catch (SQLException e) {
                System.out.println("kh dong dc " + e.getMessage());
            }
        }
    }
}
