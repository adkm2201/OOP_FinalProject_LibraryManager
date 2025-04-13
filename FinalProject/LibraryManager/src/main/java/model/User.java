package model;

/**
 *
 * @author adkm2
 */
public class User {
    private int userId;
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

    // Getters and setters userid
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    //getter setter username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //getter setter pass
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    //getter setter usertype
    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
    
    
    public String getUserTypeAsString() {
        switch (userType) {
            case 0:
                return "Reader";
            case 1:
                return "Librarian";
            case 2:
                return "Admin";
            default:
                return "Unknown";
        }
    }
}
