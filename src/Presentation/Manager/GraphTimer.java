package Presentation.Manager;

import Business.BusinessFacadeImp;
import Business.Entities.Stadistics;
import Presentation.Manager.SpotiFrameManager;
import Presentation.Ui_Views.SongsUI;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import static Presentation.DictionaryPiano.TIME_GRAPH;
import static Presentation.Manager.SpotiFrameManager.*;
import static Presentation.Ui_Views.StatisticsUI.letsInitializeGraphs;

public class GraphTimer implements ActionListener {
    /*
    Defines the timer that will control the update of the graph
     */
    private final Timer myTimer = new Timer(1000, this);

    private static final Date date = new Date();
    private final float sec = 0.01667f;

    /**
     * Constructor of the class GraphTimer
     */
    public GraphTimer() {
        myTimer.setActionCommand(TIME_GRAPH);
        myTimer.start();
    }

    /**
     * Listener that will be activated whenever there is an action event
     * @param e Defines the action event that has activated the listener
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case TIME_GRAPH:
                if (play) {
                    new BusinessFacadeImp().getSongManager().addingStadistics(new Stadistics(date.getHours(), count_song, sec));
                    if (count_song == 1) {
                        count_song = 0;
                    }
                }
                letsInitializeGraphs(getMinPlayed(), getNumSongs());
                break;
        }

    }
}
