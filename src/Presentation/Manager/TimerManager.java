package Presentation.Manager;

import Business.ChangeTime;
import Business.Entities.Observer;
import Presentation.Manager.PianoTilesUISelectorManager;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Presentation.DictionaryPiano.PIANO_TILES_TIMER;

/**
 * TimerManager
 *
 * The "TimerManager" class will contain the different methods that are needed to create and modify the Timers
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 28 June 2021
 *
 */
public class TimerManager extends Observer implements ActionListener {
    private final Timer timer;
    private final PianoTilesUISelectorManager pianoTilesUISelectorManager;

    /**
     * Constructor of the timer manager, sets the action command and some basic configurations
     */
    public TimerManager(ChangeTime changeTime, PianoTilesUISelectorManager pianoTilesUISelectorManager) {
        timer = new Timer(100, this);
        timer.setActionCommand(PIANO_TILES_TIMER);
        this.pianoTilesUISelectorManager = pianoTilesUISelectorManager;
        this.subject = changeTime;
        this.subject.attach(this);
    }

    /**
     * Makes an action depending on the actionTimer parameter
     */
    @Override
    public void update() {
        switch (subject.getActionTimer()) {
            case 0 -> timer.stop();
            case 1 -> timer.start();
            case 2 -> timer.restart();
        }
    }

    /**
     * Calls the function addTime if the action event is the PIANO_TILES_TIMER event
     * @param e Event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (PIANO_TILES_TIMER.equals(e.getActionCommand())) {
            pianoTilesUISelectorManager.addTime();
        }
    }
}

