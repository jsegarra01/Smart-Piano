package Business;

import Business.Entities.ChangeTime;
import Business.Entities.Observer;
import Presentation.Manager.PianoFrameManager;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Business.Entities.ChangeTime.actionTimer;
import static Presentation.DictionaryPiano.PIANO_TILES_TIMER;
import static Presentation.Manager.PianoTilesUISelectorManager.addTime;

/**
 * TimerManager
 *
 * The "TimerManager" class will contain the different methods that are needed to create and modify the Timers
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 9 May 2021
 *
 */
public class TimerManager extends Observer implements ActionListener {
    protected Timer timer = new Timer(100, this);

    /**
     * Constructor of the timer manager, sets the action command and some basic configurations
     */
    public TimerManager(ChangeTime changeTime) {
        timer.setActionCommand(PIANO_TILES_TIMER);
        this.subject = changeTime;
        this.subject.attach(this);
    }

    /**
     * Makes an action depending on the actionTimer parameter
     */
    @Override
    public void update() {
        switch (actionTimer) {
            case 0:
                timer.stop();
                break;
            case 1:
                timer.start();
                break;
            case 2:
                timer.restart();
                break;
        }
    }

    /**
     * Calls the function addTime if the action event is the PIANO_TILES_TIMER event
     * @param e Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (PIANO_TILES_TIMER.equals(e.getActionCommand())) {
            addTime();
        }
    }
}

