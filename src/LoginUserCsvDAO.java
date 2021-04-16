import java.sql.*;
public class LoginUserCsvDAO {

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

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
