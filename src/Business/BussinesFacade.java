package Business;

import Business.Entities.User;


public interface BussinesFacade {

    Boolean logIn(String username, String password);
    Boolean SignUp(String username, String mail, String password, String passwordConfirm);
}
