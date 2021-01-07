package model;

public class User {

    /**
     * The User has:
     *     fullName - should contain 2 words separated by space and have only letters
     *     username -  username length should be more than 10 characters
     *     password - the password length should be more than 8 characters, should have at least 2 uppercase
     *                letters and 3 digits
     *     email - email vaidation should be done through regular expression
     */

    private static final String ADMIN_USERNAME = "nelliAn111AAA";
    private static final String ADMIN_PASSWORD = "123456aA!!!!AAA";

    private static int userNumbers = 1;

    private String fullName;
    private String username;
    private String password;
    private String email;

    public User(String data) {
        String[] s = data.split(",");
        this.fullName = s[0];
        this.username = s[1];
        this.password = s[2];
        this.email = s[3];
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String getAdminUsername() {
        return ADMIN_USERNAME;
    }

    public static String getAdminPassword() {
        return ADMIN_PASSWORD;
    }

    @Override
    public String toString() {
        return "User " + userNumbers++ +
                " - " + fullName +
                ", " + username +
                ", " + password +
                ", " + email;
    }
}