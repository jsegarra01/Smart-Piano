package Business.Entities;

import Business.ChangeTime;

/**
 * Observer
 *
 * The abstract class "Observer" will be control every time there has been a change in time and will update its
 * respective classes
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 28 June 2021
 */
public abstract class Observer {

    /*
    Defines the attribute that controls time in the observer
     */
    protected ChangeTime subject;

    /**
     * Method that updates the time (to be overwritten)
     */
    public abstract void update();
}
