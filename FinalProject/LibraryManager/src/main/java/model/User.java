/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author adkm2
 */
public abstract class User {
    private int id;
    private String username;
    String password;
    boolean isLib;
    boolean isAdmin;
    
    public User() {
    }
    
    public User(int id, String username, String password, boolean isLib, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isLib = isLib;
        this.isAdmin = false;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for isLib
    public boolean isLib() {
        return isLib;
    }

    public void setLib(boolean isLib) {
        this.isLib = isLib;
    }
}
