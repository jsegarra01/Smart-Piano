package Business.Entities;

/**
 * User
 *
 * The "User" class will contain the getters and setters from the user
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 28 June 2021
 *
 */
public class User {

    /*
    Defines the username of the user
     */
    private final String userName;

    /*
    Defines the mail of the user
     */
    private final String mail;

    /*
    Defines the password of the user
     */
    private final String password;

    /**
     * Constructor of a user
     * @param userName User's name
     * @param mail User's mail
     * @param password User's password
     */
    public User(String userName, String mail, String password){
        this.userName = userName;
        this.mail = mail;
        this.password = password;
    }

    /**
     * Method that gets the mail
     * @return String that stores the mail of the user
     */
    public String getMail() {
        return mail;
    }

    /**
     * Method that gets the password
     * @return String that stores the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method that gets the username
     * @return String that stores the username of the user
     */
    public String getUserName() {
        return userName;
    }
}
