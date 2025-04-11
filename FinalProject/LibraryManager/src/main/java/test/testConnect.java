/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author adkm2
 */
public class testConnect {
    public static void main(String[] args) throws SQLException {
        Connection connection = database.Database.connect();
        System.out.println(connection);
    }
}
