package Business;

/**
 * BusinessFacade
 *
 * The "BusinessFacade" class will contain the implementation of the BusinessFacade interface to connect the views with the logic and the database
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 24 Apr 2021
 *
 */
public class BusinessFacadeImp implements Business.BusinessFacade {
    private UserManager loginUserManager = new UserManager();
    private SongManager songpanager = new SongManager();
    private PlaylistManager playlistManager = new PlaylistManager();

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

    @Override
    public void deleteAccount() {
        loginUserManager.deleteUser();
    }

    public PlaylistManager getPlaylistManager() {
        return playlistManager;
    }
}
