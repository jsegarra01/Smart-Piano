package Business.Entities;

/**
 * User
 *
 * The "User" class will contain the getters and setters from the user
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 21 Apr 2021
 *
 */
public class User {
    private String userName;
    private String mail;
    private String password;

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
     * Sets the username
     * @param userName Desidered username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Sets the mail
     * @param mail Desired mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Sets the password
     * @param password Desired password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the mail
     * @return Mail of the user
     */
    public String getMail() {
        return mail;
    }

    /**
     * Gets the password
     * @return Password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the username
     * @return Username of the user
     */
    public String getUserName() {
        return userName;
    }
}
