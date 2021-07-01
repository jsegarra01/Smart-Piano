package Presentation.Manager;

import Business.BusinessFacadeImp;
import Business.Entities.Stadistics;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import static Presentation.DictionaryPiano.TIME_GRAPH;

/**
 * GraphTimer
 *
 * The "GraphTimer" class will contain the different methods that are needed to control the view class "Graph"
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 28 June 2021
 *
 */
public class GraphTimer implements ActionListener {
    private final SpotiFrameManager spotiFrameManager;

    /**
     * Constructor of the class GraphTimer
     * @param spotiFrameManager controller spotiFrame
     */
    public GraphTimer(SpotiFrameManager spotiFrameManager) {
        //Defines the timer that will control the update of the graph
        this.spotiFrameManager = spotiFrameManager;
        Timer myTimer = new Timer(1000, this);
        myTimer.setActionCommand(TIME_GRAPH);
        myTimer.start();
    }

    /**
     * Listener that will be activated whenever there is an action event
     * @param e Defines the action event that has activated the listener
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (TIME_GRAPH.equals(e.getActionCommand())) {
            if (spotiFrameManager.getPlay()) {
                Calendar calendar = Calendar.getInstance();
                BusinessFacadeImp.getBusinessFacade().addStats(new Stadistics(calendar.get(Calendar.HOUR_OF_DAY), spotiFrameManager.getCount_song(), 0.01667f));
                if (spotiFrameManager.getCount_song() == 1) {
                    spotiFrameManager.setCount_song(0);
                }
            }
            spotiFrameManager.getStatisticsFrame().letsInitializeGraphs(spotiFrameManager.getMinPlayed(), spotiFrameManager.getNumSongs());
        }
    }
}
