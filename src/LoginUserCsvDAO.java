import java.sql.*;
public class LoginUserCsvDAO implements LoginUserDAO{

    public LoginUserCsvDAO(){
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://"+
                    ReadConfigJson.getConfigJson().getIpAddress() + ":" +
                    ReadConfigJson.getConfigJson().getPort()+"/"+ReadConfigJson.getConfigJson().getName(),
                    ReadConfigJson.getConfigJson().getUsername(), ReadConfigJson.getConfigJson().getPassword());

            Statement statement = myConn.createStatement();

            ResultSet myRs = statement.executeQuery("select * from User");

            while (myRs.next()){
                System.out.println(myRs.getString("username") +
                        myRs.getString("email") +
                        myRs.getString("password"));
            }
            myConn.close();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
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
        return null;
    }

    @Override
    public User getByMail(String myMail) {
        return null;
    }
}
