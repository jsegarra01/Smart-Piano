package Business.Entities;


import Business.ChangeTime;

/**
 * Observer
 *
 * The abstract class "Observer" will be control every time there has been a change in time and will update its
 * respective classes
 */
public abstract class Observer {
    protected ChangeTime subject;
    public abstract void update();
}
