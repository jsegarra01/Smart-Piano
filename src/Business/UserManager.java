package Business;

//Imports needed to connect the persistence layer and the business layer
import Business.Entities.User;
import Persistence.LoginUserDAO;
import Persistence.SQL.Csv.LoginUserCsvDAO;

import java.sql.SQLException;
import java.util.regex.Pattern;

/**
 * BusinessFacade
 *
 * The "BusinessFacade" class will contain the implementation of the BusinessFacade interface to connect the views with the logic and the database
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 24 Apr 2021
 *
 */
public class UserManager {
    private final LoginUserDAO loginUserManager = new LoginUserCsvDAO();
    private static User user;

    public static User getUser() {
        return user;
    }

    /**
     * Checks if the user exists or not
     * @param username Username string which the user has inputted while logging in
     * @param password Password string which the user has inputted while logging in
     * @return Boolean. If it is a 1, the user exists. If it is a 0, it doesn't.
     */
    public boolean checkUser(String username, String password) {
        user = loginUserManager.getByUsername(username);
        boolean returned = false;
        if (user == null) {
            if (username.equals("guest")) {
                try {
                    signUser(username, "WeLoveChallenge@lasal.com", password);
                    return true;
                } catch (SQLException e) {
                    BusinessFacadeImp.getBusinessFacade().setError(0);
                    return false;
                }
            }
        }
        else {
            returned = user.getPassword().equals(password);
        }

        if (!returned) {
            BusinessFacadeImp.getBusinessFacade().setError(2);
        }
        return returned;
    }

    /**
     * Deletes the user inserted
      */
    public boolean deleteUser() throws SQLException {
        if (!user.getUserName().equals("guest")) {
            return loginUserManager.delete(user);
        }
        else {
            return false;
        }
    }

    /**
     * Tries to create a new user with the data inserted
     * @param username Username string which the user has inputted while signing up
     * @param mail Mail string which the user has inputted while signing up
     * @param password Password string which the user has inputted while signing up
     * @return Boolean. If it can create a new user, returns 1. Else, returns 0
     */
    public boolean signUser(String username, String mail, String password) throws SQLException {
        if (isValid(mail)) {
            user = new User(username, mail, password);
            return loginUserManager.save(user);
        }
        return false;
    }

    /**
     * Checks if the mail format is correct or not
     * @param email Mail inserted by the user in order to check if it is in a correct format
     * @return Boolean. If it is correct the format, returns 1. If not, else
     */
    private boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

}
