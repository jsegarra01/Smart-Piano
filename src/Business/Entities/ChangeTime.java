package Business.Entities;

import Business.TimerManager;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: SEGUEIXO SENSE SABER QUE FA UN OBSERVER
 */
public class ChangeTime {
    private static List<Observer> observers = new ArrayList<Observer>();
    public static int actionTimer = 0;

    public ChangeTime(){
        new TimerManager(this);
    }

    public ChangeTime(int state) {
        actionTimer = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
