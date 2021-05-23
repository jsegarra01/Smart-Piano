package Presentation.Manager;

import Business.BusinessFacadeImp;
import Business.Entities.Stadistics;
import Presentation.Manager.SpotiFrameManager;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import static Presentation.DictionaryPiano.TIME_GRAPH;
import static Presentation.Manager.SpotiFrameManager.*;
import static Presentation.Ui_Views.StatisticsUI.letsInitializeGraphs;

public class GraphTimer implements ActionListener {
    private Timer myTimer = new Timer(1000, this); //  = new Timer(1000, this);
    private static final Date date = new Date();


    public GraphTimer() {
        myTimer.setActionCommand(TIME_GRAPH);
        myTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case TIME_GRAPH:
                if (play) {
                    lastMin = System.currentTimeMillis();
                    minPlayed = (float)(lastMin - startMin)/60000;
                    startMin = System.currentTimeMillis();
                    new BusinessFacadeImp().getSongManager().addingStadistics(new Stadistics(date.getHours(), count_song, 0.01667f));
                    if (count_song == 1) {
                        count_song = 0;
                    }
                }
                letsInitializeGraphs(getMinPlayed(), getNumSongs());
                break;
        }

    }
}
