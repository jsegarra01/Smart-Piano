package Persistence;

import Business.Entities.User;

import java.sql.SQLException;

/**
 * Interface that abstracts the persistence of groups from upper layers.
 *
 * <p>In particular, it follows the Data Access Object design pattern, which is commonly used to abstract persistence
 * implementations with a set of generic operations.
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 22 Apr 2021
 */
public interface LoginUserDAO {

    /**
     * Method that checks if the user passed as a parameter exists or not and saves in the db in case it does not
     * @param myUser Defines the user that may be stored in the db
     * @return Boolean that returns a true if it has stored a user and a false if it has not
     */
    boolean save(User myUser) throws SQLException;

    /**
     * Method that is in charge of deleting the user passed in the parameter
     * @param myUser Defines the user that will be deleted from the db
     * @return boolean: In case the user exists, returns 1, if it doesn't, returns 0
     */
    boolean delete (User myUser) throws SQLException;

    /**
     * Method that gets the user according to the userName passed in the parameter and closes the connection.
     * @param myUserName Defines the username of the User
     * @return Class that stores the User
     */
    User getByUsername(String myUserName);

    /**
     * Method that gets the user according to the mail passed in the parameter and closes the connection.
     * @param myMail Defines the mail of the User
     * @return Class that stores the User
     */
    User getByMail(String myMail);
}
