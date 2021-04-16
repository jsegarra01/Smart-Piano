import java.sql.*;
public class LoginUserCsvDAO implements LoginUserDAO{

    private Connection connection;

    private void makeConnection() throws SQLException {
            connection =  DriverManager.getConnection("jdbc:mysql://"+
                            ReadConfigJson.getConfigJson().getIpAddress() + ":" +
                            ReadConfigJson.getConfigJson().getPort()+"/"+ReadConfigJson.getConfigJson().getName(),
                    ReadConfigJson.getConfigJson().getUsername(), ReadConfigJson.getConfigJson().getPassword());

    }
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


    @Override
    public void save(User myUser) {

    }

    @Override
    public void update(User myUser) {

    }

    @Override
    public void delete(User myUser) {

    }

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
