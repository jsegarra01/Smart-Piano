import java.sql.*;
public class LoginUserCsvDAO implements LoginUserDAO{

    /**
     * Stores the information that is being used to connect to the database
     */
    private Connection connection;

    /**
     * Method that is in charge of creating the connection with the database which configuration is in the config.json
     * file
     * @throws SQLException Throw that makes an exception if there has been any error while making the connection.
     *                      It will be handled with the try catch from where it is called.
     */
    private void makeConnection() throws SQLException {
            connection =  DriverManager.getConnection("jdbc:mysql://"+
                            ReadConfigJson.getConfigJson().getIpAddress() + ":" +
                            ReadConfigJson.getConfigJson().getPort()+"/"+ReadConfigJson.getConfigJson().getName(),
                    ReadConfigJson.getConfigJson().getUsername(), ReadConfigJson.getConfigJson().getPassword());

    }

    /**
     * Method that gets the user from the database
     * @param myUserString Defines either the email or the username of the user who we want to get
     * @param state Defines either the attribute email or username, in order to get the desired user.
     * @return Class that stores the User
     */
    private User userFromCsv(String myUserString, String state){
        try {
            makeConnection();
            ResultSet myRs = connection.createStatement().executeQuery("select * from User as u where u." + state + "= '" + myUserString + "'");
            //myRs.close();
            return myRsToUser(myRs);

        } catch (SQLException throwable) {
            throwable.printStackTrace();
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
            return new User(
                    myRs.getString("username"),
                    myRs.getString("email"),
                    myRs.getString("password"));
        }else{
            return null;
        }

    }

    /**
     * Method that writes into the database the user that is being passed as a parameter
     * @param myUser Defines the user that will be stored in the db
     */
    private void userToCsv(User myUser){
        try {
            makeConnection();
            //ResultSet myRs = connection.createStatement().executeQuery("insert into User values ('" + "alex" + "', '" +
                    //myUser.getMail() + "', '" + myUser.getPassword() + "')");
            //myRs.close();
            PreparedStatement st = connection.prepareStatement("insert into User values ('" + myUser.getUserName() + "', '" +
                    myUser.getMail() + "', '" + myUser.getPassword() + "')");
            st.execute();
            connection.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    private void closeConnection (ResultSet myRs) throws SQLException {
        myRs.close();
        connection.close();
    }

    /**
     * Method that checks if the user passed as a parameter exists or not and saves in the db in case it does not
     * @param myUser Defines the user that may be stored in the db
     * @return Boolean that returns a true is it has stored a user and a false if it has not
     */
    @Override
    public boolean save(User myUser) {
        if(getByUsername(myUser.getUserName()) == null && getByMail(myUser.getMail())==null){
            userToCsv(myUser);
            return true;
        }
        else{
            return false;
        }
    }
/*
    @Override
    public void update(User myUser) {

    }*/

    /**
     * Method that is in charge of deleting the user passed in the parameter
     * @param myUser Defines the user that will be deleted from the db
     */
    @Override
    public void delete(User myUser) {
        try {
            makeConnection();
            PreparedStatement st = connection.prepareStatement("delete from User where username = '" + myUser.getUserName() + "'");
            st.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * Method that gets the user according to the userName passed in the parameter and closes the connection.
     * @param myUserName Defines the username of the User
     * @return Class that stores the User
     */
    @Override
    public User getByUsername(String myUserName) {
        User user = userFromCsv(myUserName, "username");
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    /**
     * Method that gets the user according to the mail passed in the parameter and closes the connection.
     * @param myMail Defines the mail of the User
     * @return Class that stores the User
     */
    @Override
    public User getByMail(String myMail) {
        User user = userFromCsv(myMail, "email");
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;

    }
}
