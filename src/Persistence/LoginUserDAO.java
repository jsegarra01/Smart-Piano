package Persistence;

import Business.Entities.User;

/**
 * Interface that abstracts the persistence of groups from upper layers.
 *
 * <p>In particular, it follows the Data Access Object design pattern, which is commonly used to abstract persistence
 * implementations with a set of generic operations.
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 22 Apr 2021
 */
public interface LoginUserDAO {

    /**
     *
     * @param myUser
     * @return
     */
    boolean save(User myUser);
    //void update (Business.Entities.User myUser);

    /**
     *
     * @param myUser
     */
    void delete (User myUser);

    /**
     *
     * @param myUserName
     * @return
     */
    User getByUsername(String myUserName);

    /**
     *
     * @param myMail
     * @return
     */
    User getByMail(String myMail);
}
