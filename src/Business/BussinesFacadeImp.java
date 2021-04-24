package Business;

import Business.LoginUserManager;

public class BussinesFacadeImp implements Business.BussinesFacade {
    LoginUserManager loginUserManager = new LoginUserManager();

    @Override
    public Boolean logIn(String username, String password) {
        return loginUserManager.checkUser(username, password);
    }

    @Override
    public Boolean SignUp(String username, String mail, String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm)) {
            return false;
        }
        return loginUserManager.signUser(username,mail,password);
    }
}
