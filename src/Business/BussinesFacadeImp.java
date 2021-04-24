package Business;

import Business.LoginUserManager;

public class BussinesFacadeImp implements Business.BussinesFacade {

    LoginUserManager loginUserManager = new LoginUserManager();
    @Override
    public Boolean logIn(String username, String password) {
        return loginUserManager.checkUser(username, password);
    }
}
