package Persistence.SQL.Csv;

import Business.Entities.Song;
import Business.Entities.User;
import Persistence.LoginUserDAO;
import Persistence.SQL.ConnectSQL;

import java.sql.*;

/**
 * Class that implements the methods described in {@link LoginUserDAO} interface, and will be used as a way to
 * physically separate the persistence layer from the rest of the application.
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 22 Apr 2021
 */
public class LoginUserCsvDAO implements LoginUserDAO{
    /*
    The class LoginUserCsvDao allows us to access the information stored in the database regarding the users. It let us
    access to the users in the database by their username or their email. This feature is implemented so that the login
    can be checked. It is checked depending on what the user has written the email or the username and returns a boolean.
    This class is also used to modify the tables, by deleting a user or by inserting a new user.
     */


    /**
     * Method that gets the user from the database
     * @param myUserString Defines either the email or the username of the user who we want to get
     * @param state Defines either the attribute email or username, in order to get the desired user.
     * @return Class that stores the User
     */
    private User userFromCsv(String myUserString, String state) throws SQLException {
        if(ConnectSQL.getInstance() != null){
            ResultSet myRs = ConnectSQL.getInstance().createStatement().executeQuery("select * from UserT as u where u." + state +
                    "= '" + myUserString + "'");
            return myRsToUser(myRs);
        }else{
            return null;
        }
    }

    /**
     * Method that converts the query saved in a ResultSet interface and saves it in a variable User
     * @param myRs Defines the query saved to be transformed
     * @return Class that stores the User
     * @throws SQLException Throw that makes an exception if there has been any error while making the connection.
     *                      It will be handled with the try catch from where it is called.
     */
    private User myRsToUser(ResultSet myRs) throws SQLException {

        if(myRs.next()){
            User user = new User(
                    myRs.getString("username"),
                    myRs.getString("email"),
                    myRs.getString("password"));
            myRs.close();
            return user;
        }else{
            return null;
        }

    }

    /**
     * Method that writes into the database the user that is being passed as a parameter
     * @param myUser Defines the user that will be stored in the db
     */
    private boolean userToCsv(User myUser) throws SQLException{
        if(ConnectSQL.getInstance()!=null){
                PreparedStatement st = ConnectSQL.getInstance().prepareStatement("insert into UserT values ('" + myUser.getUserName() +
                        "', '" + myUser.getMail() + "', '" + myUser.getPassword() + "')");
                st.execute();
                return true;
        }
        else{
            return false;
        }

    }

    /**
     * Method that checks if the user passed as a parameter exists or not and saves in the db in case it does not
     * @param myUser Defines the user that may be stored in the db
     * @return Boolean that returns a true if it has stored a user and a false if it has not
     */
    @Override
    public boolean save(User myUser) throws SQLException{
        if(getByUsername(myUser.getUserName()) == null && getByMail(myUser.getMail()) == null){
            return userToCsv(myUser);
        }
        else{
            return false;
        }
    }

    /**
     * Method that is in charge of deleting the user passed in the parameter
     * @param myUser Defines the user that will be deleted from the db
     * @return boolean: In case the user exists, returns 1, if it doesn't, returns 0
     */
    @Override
    public boolean delete(User myUser) throws SQLException{
        if(ConnectSQL.getInstance()!=null){
                PreparedStatement st = ConnectSQL.getInstance().prepareStatement("delete from UserT where username = '" + myUser.getUserName()+"'");
                st.execute();
                return true;
        }
        else{
            return false;
        }

    }

    /**
     * Method that gets the user according to the userName passed in the parameter and closes the connection.
     * @param myUserName Defines the username of the User
     * @return Class that stores the User
     */
    @Override
    public User getByUsername(String myUserName) {
        try {
            return userFromCsv(myUserName, "username");
        } catch (SQLException throwables) {
            return null;
        }
    }

    /**
     * Method that gets the user according to the mail passed in the parameter and closes the connection.
     * @param myMail Defines the mail of the User
     * @return Class that stores the User
     */
    @Override
    public User getByMail(String myMail) {
        try {
            return userFromCsv(myMail, "email");
        } catch (SQLException throwables) {
            return null;
        }

    }


}
