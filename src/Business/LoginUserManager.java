package Business;

import Business.Entities.User;
import Persistence.SQL.Csv.LoginUserCsvDAO;

public class LoginUserManager {

    LoginUserCsvDAO loginUserManager = new LoginUserCsvDAO();

    public boolean checkUser(String username, String password) {
        User user;
        user = loginUserManager.getByUsername(username);

        if(user == null) return false;

        return user.getPassword().equals(password);
    }

    public void deleteUser(String username) {
        User user = loginUserManager.getByUsername(username);

        loginUserManager.delete(user);
        System.out.println(username);
    }

    public Boolean signUser(String username, String mail, String password) {
        return loginUserManager.save(new User(username,mail,password));
    }
}
