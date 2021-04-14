import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginUserCsvDAO {



    public LoginUserCsvDAO(String myIp, int myPort){
        try {
            Connection myConn = DriverManager.getConnection("HOLA");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
