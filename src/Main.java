/*
 * The "Main" class will run the program
 */
import com.mysql.cj.log.Log;

import java.sql.*;
public class Main {

    public static void main(String[] args) {
        //TODO put this ReadConfigJson to the controller.
        //Obtains the information from the readConfigJson().
        ReadConfigJson.readConfigJson();
        LoginUserCsvDAO log = new LoginUserCsvDAO();
        System.out.println(log.getByMail("laura.nuez@students.salle.url.edu").getPassword());
       //log.delete(log.getByUsername("styopartist"));
        //System.out.println(log.getByMail("stepan.batllori@students.salle.url.edu").getPassword());
    }
}
