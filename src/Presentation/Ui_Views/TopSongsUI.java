package Presentation.Ui_Views;

import Business.Entities.ButtonColumn;
import Business.Entities.Song;
import Presentation.Manager.SpotiFrameManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class TopSongsUI extends JPanel {
    private final LinkedList<Float> numPlayed = new LinkedList<>();

    public TopSongsUI() {
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(Color.white);
    }

    private LinkedList<Float> getTopSongs(LinkedList<Float> numPlayed){
        //LinkedList<Float> aux = numPlayed;
        //aux.sort();
        return numPlayed;
    }
}
