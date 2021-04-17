public interface LoginUserDAO {
    boolean save(User myUser);
    //void update (User myUser);
    void delete (User myUser);
    User getByUsername(String myUserName);
    User getByMail(String myMail);
}
