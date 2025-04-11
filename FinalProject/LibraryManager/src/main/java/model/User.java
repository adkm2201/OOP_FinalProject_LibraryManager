/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author adkm2
 */
public class User {
    //private int userId;
    private String username;
    private String password;
    private int userType; // 0: Reader, 1: Librarian, 2: Admin

    public User() {}

    public User(String username, String password, int userType) {
        //this.userId = userId;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    // Getters and setters
//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
