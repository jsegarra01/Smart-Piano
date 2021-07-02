package Business;

import Business.Entities.Observer;
import Presentation.Manager.PianoTilesUISelectorManager;
import Presentation.Manager.TimerManager;

import java.util.ArrayList;
import java.util.List;

/**
 * ChangeTime
 *
 * The "ChangeTime" class will control and observe the different tiles that fall
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 28 June 2021
 *
 */
public class ChangeTime {

    /*
    Defines a list of observers
     */
    private static final List<Observer> observers = new ArrayList<>();

    /*
    Defines the action timer
     */
    private static int actionTimer = 0;

    /**
     * Constructor of ChangeTimer
     */
    public ChangeTime(PianoTilesUISelectorManager pianoTilesUISelectorManager) {
        new TimerManager(this, pianoTilesUISelectorManager);
    }

    /**
     * Constructor of ChangeTimer that changes depending on the state passed in the parameter. It can create a new instance of the TimerManager
     * or it can define the action of the timer
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
     * Method that updates all the observers
     */
    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }

    /**
     * Method that gets the action timer
     * @return Int that stores the action timer
     */
    public int getActionTimer() {
        return actionTimer;
    }
}
