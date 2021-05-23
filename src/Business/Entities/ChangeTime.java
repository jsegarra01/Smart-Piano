package Business.Entities;

import Business.TimerManager;

import java.util.ArrayList;
import java.util.List;

/**
 * ChangeTime
 *
 * The "ChangeTime" class will control and observe the different tiles that fall
 */
public class ChangeTime {

    /*
    Defines a list of observers
     */
    private static final List<Observer> observers = new ArrayList<>();
    public static int actionTimer = 0;

    /**
     * Constructor of ChangeTimer creating a new instance of the TimerManager
     */
    public ChangeTime(){
        new TimerManager(this);
    }

    /**
     * Constructor of ChangeTimer that changes depending on the state passed in the parameter
     * @param state Defines the action timer
     */
    public ChangeTime(int state) {
        actionTimer = state;
        notifyAllObservers();
    }

    /**
     * Method that adds the observer to the list of observers
     * @param observer Defines the observer to be added in the lsit
     */
    public void attach(Observer observer){
        observers.add(observer);
    }

    /**
     * Method that update all the observers
     */
    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
