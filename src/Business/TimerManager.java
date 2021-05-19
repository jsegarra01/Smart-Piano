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

public class TimerManager extends Observer implements ActionListener {
    protected Timer timer = new Timer(100, this);

    public TimerManager(ChangeTime changeTime) {
        timer.setActionCommand(PIANO_TILES_TIMER);
        this.subject = changeTime;
        this.subject.attach(this);
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (PIANO_TILES_TIMER.equals(e.getActionCommand())) {
            addTime();
        }
    }
}

