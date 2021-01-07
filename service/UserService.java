package service;

import model.User;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static service.FileService.createFile;

public class UserService {

    /**
     * In the database.txt file, the user info is saved and that PATH will be used in a few places in the UserService
     * class.
     */

    public static final String PATH = "C:\\Users\\ACER\\IdeaProjects\\Homework8\\src\\database.txt";

    /**
     * The email validation is done with regular expression.
     */

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     * The regular expression provides the correct email format.
     * @param email
     * @return
     */
    public static boolean validateEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    /**
     * In fillUserInfo function, firstly it's checked whether createFile function returns true or false (it returns
     * false if the file already exists. If the file doesn't exist, then it creates that file and returns true).
     * If there are no registered users, fillUserInfo function will return null, otherwise it will convert the
     * file info to users.
     * @return
     */

    public static List<User> fillUserInfo() {

        //let's take the database.txt file
        String databaseFileName = "database.txt";
       // String databaseFileName = PATH.substring(PATH.lastIndexOf('\\') + 1).trim();

        List<User> users = null;

        if (createFile(databaseFileName)) {
            System.out.println("There are no registered users." +
                    "\nPlease choose one of the other options or return to the Admin Main Menu.");
            return null;
        } else {
            try {
                users = convert(FileService.read(PATH));
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            System.out.println("-----------------------");
        }
        return users;
    }

    /**
     * After reading from the database.txt file, the info is filled in the User array and users are created.
     * If any of the info related to the user is missing from the file, "That row does not have all the user
     * information" message will be printed.
     * @param read
     * @return
     */

      /*
    public User[] fillUserInfo(List<String> read) {

        int defaultUserMembers = 4;
        int index = 0;
        User[] users = new User[read.size()];

        for (int i = 0; i < read.size(); i++) {
            String[] member = read.get(i).split(",");
            if (member.length == defaultUserMembers) {
                users[index++] = new User(member[0], member[1], member[2], member[3]);
            } else {
                System.out.println("Row " + i + " does not have all the user information.");
            }
        }
        return users;
    }
    */

    /**
     * convert function converts the List<String> into Users.
     * @param data
     * @return
     */

    private static List<User> convert(List<String> data) {
        List<User> users = new ArrayList<>();
        for (String x : data) {
            users.add(new User(x));
        }
        return users;
    }

    /**
     * The user types the username and password. The user will need to type the correct format for the
     * username and password. Username length should be more than 10 and the password should be valid.
     */

    public String[] fillLoginInfo() {

        String username, password;

        while (true) {
            System.out.println("Please type a username:");
            Scanner sc = new Scanner(System.in);
            username = sc.next();
            if (username.length() > 10) {
                break;
            }
        }

        while (true) {
            System.out.println("Please type a password:");
            Scanner sc = new Scanner(System.in);
            password = sc.next();
            if (validPassword(password)) {
                break;
            }
        }

        return new String[]{username, password};
    }

    /**
     * The password should be valid - the length should be more than 8 characters, should have at least 2 uppercase
     * letters and 3 digits.
     * @param password
     * @return
     */

    public static boolean validPassword(String password) {
        int countUpperCase = 0, countDigit = 0;
        if (password.length() > 8) {
            for (int i = 0; i < password.length(); i++) {
                if (Character.isUpperCase(password.charAt(i))) {
                    countUpperCase++;
                }
                if (Character.isDigit(password.charAt(i))) {
                    countDigit++;
                }
            }
            if (countUpperCase >= 2 && countDigit >= 3)
                return true;
        }
        return false;
    }

    /**
     * You can login as a user or an admin, thus the username and password should be checked whether it's correct
     * or not when comparing with the admin credentials.
     * @param username
     * @param password
     * @return
     */
    public static boolean loginAdminValidation(String username, String password) {
        if(User.getAdminUsername().equals(username) && User.getAdminPassword().equals(password)){
            System.out.print("You have logged in successfully. ");
            return true;
        }
        return false;
    }

    /**
     * The username and password should be checked whether it's correct or not from the database.txt file.
     * @param username
     * @param password
     * @return
     */

    public static boolean loginValidation(String username, String password) {

        /**
         * Firstly, it should be checked whether the database.txt file exists or not.
         * If the file exists, it will read from the file and create all the users and check the
         * username and password accordingly.
         * If the file doesn't exist or the credentials don't match, "Wrong username or password." message will appear
         * and the user will need to choose whether to login or register again that is why we return boolean for the
         * loop.
         */

        try {
            File file = new File(PATH);
            boolean exists = file.exists();
            if (exists) {

                /**
                 * The database.txt file info is read and saved as List<String> and users are created in fillUserInfo
                 * function.
                 */

                List<String> read = FileService.read(PATH);

                List<User> users = fillUserInfo();

                int index = 0;

                /**
                 * The user typed password will be encrypted and then compared as the passwords in the database.txt
                 * file are already encrypted.
                 * If the user typed username is equal to the username in the file, then the index is saved
                 * and the encrypted password values are checked. If those are also equal, then the login info is
                 * valid.
                 * The username is unique as when the user registers, it is always checked whether that username
                 * already exists or not.
                 */

                String encryptPass = md5(password);

                for (int i = 0; i < read.size(); i++) {
                    if (users.get(i).getUsername().equals(username)) {
                        index = i;
                        break;
                    }
                }

                if (users.get(index).getPassword().equals(encryptPass)) {
                    System.out.print("You have logged in successfully. ");
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println("Wrong username or password.");
        return false;
    }

    /**
     * The user types the full name, username, email and password. The user will need to type the correct format
     * for each of them.
     */

    public String[] fillRegistrationInfo() {

        String fullName, username, email, password;

        /**
         * The full name should consist of 2 words and be split by the space and
         * both strings should only contain letters.
         */
        while (true) {
            System.out.println("Please type a full name.");
            Scanner sc2 = new Scanner(System.in);
            fullName = sc2.nextLine();
            String[] words = fullName.split(" ");
            if (words.length == 2 && words[0].matches("^[a-zA-Z]*$") &&
                    words[1].matches("^[a-zA-Z]*$"))
                break;
        }

        /**
         * The username length should be more than 10.
         */
        while (true) {
            System.out.println("Please type a username.");
            Scanner sc2 = new Scanner(System.in);
            username = sc2.next();
            if (username.length() > 10) {
                break;
            }
        }

        /**
         * The user should type a valid email.
         */
        while (true) {
            System.out.println("Please type an email.");
            Scanner sc2 = new Scanner(System.in);
            email = sc2.next();
            boolean result = validateEmail(email);
            if (result) {
                //email is valid
                break;
            }
        }

        /**
         * The user should type a valid password.
         */

        while (true) {
            System.out.println("Please type a password.");
            Scanner sc2 = new Scanner(System.in);
            password = sc2.next();
            if (validPassword(password)) {
                break;
            }
        }
        return new String[]{fullName, username, email, password};
    }

    /**
     * newUser() function checks whether the database.txt file exists or not.
     * If the file doesn't exist, the createFile function will return true and then it will create the file and
     * check whether the username and password are equal to the admin credentials or not.
     * If they are equal, then it returns false as the user cannot register with the admin credentials.
     * Otherwise, if the credentials are not equal, the user will be registered and the info will be added to the
     * database.txt file and newUser function will return true.
     * If the file exists, createFile function will return false and the checkUser function will be called,
     * which will check whether the credentials are correct or not.
     * @param fullName
     * @param username
     * @param email
     * @param password
     * @return
     */

    public static boolean newUser(String fullName, String username, String email, String password) {

        if (FileService.createFile("database.txt")) {

            if (User.getAdminUsername().equals(username) && User.getAdminPassword().equals(password)) {
                System.out.println("You are already logged in. Please Login.");
                return false;
            } else {
                try {
                    Files.write(Paths.get(PATH), fullName.getBytes());
                    FileService.write(PATH, "," + username);
                    FileService.write(PATH, "," + email);
                    FileService.write(PATH, "," + md5(password) + "\n");
                } catch (Exception exception) {
                    System.out.println("An error occurred.");
                    exception.printStackTrace();
                }
                return true;
            }
        }
        return checkUser(fullName, username, email, password);
    }

    /**
     * In checkUser() function, the database.txt file already exists and the user typed username and email are
     * firstly compared to the admin credentials and then to the file username and emails.
     * If the user typed username and password are unique and aren't equal to any of the ones in the file, then the
     * user can be registered.
     * Otherwise, the user will get the message "You are already logged in. Please Login." and will need to choose
     * whether to login or register again.
     * @param fullName
     * @param username
     * @param email
     * @param password
     * @return
     */

    public static boolean checkUser(String fullName, String username, String email, String password) {

        if (User.getAdminUsername().equals(username) && User.getAdminPassword().equals(password)) {
            System.out.println("You are already logged in. Please Login.");
            return false;
        } else {
            try {
                List<String> read = FileService.read(PATH);

                List<User> users = fillUserInfo();

                boolean temp = false;
                for (int i = 0; i < read.size(); i++) {
                    User x = users.get(i);
                    if (x.getEmail().equals(email) || x.getUsername().equals(username)) {
                        temp = true;
                    }
                }

                if (!temp) {
                    try {
                        FileService.write(PATH, fullName);
                        FileService.write(PATH, "," + username);
                        FileService.write(PATH, "," + email);
                        FileService.write(PATH, "," + md5(password) + "\n");
                        return true;
                    } catch (Exception exception) {
                        System.out.println("An error occurred.");
                        exception.printStackTrace();
                    }
                } else {
                    System.out.println("You are already logged in. Please Login.");
                }

            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            return false;
        }
    }

    /**
     * The password should be encrypted.
     * @param password
     * @return
     */

    public static String md5(String password) {

        String md5 = null;

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(password.getBytes(), 0, password.length());
            md5 = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }
}