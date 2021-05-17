package Presentation.Ui_Views;

import Presentation.Manager.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class TopSongsUI extends JPanel {
    private MainFrame mainFrame;
    private LinkedList<Float> numPlayed = new LinkedList<Float>();

    public TopSongsUI(final MainFrame mainFrame/*, LinkedList<Float> numPlayed*/) {
        super();
        this.mainFrame = mainFrame;
        //this.numPlayed = numPlayed;
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(Color.black);
    }

    private LinkedList<Float> getTopSongs(LinkedList<Float> numPlayed){
        LinkedList<Float> aux = numPlayed;
        //aux.sort();
        return aux;
    }

    /*
    * Float maxY = Float.MIN_VALUE;
        for (Float myY : yPoints) {
            maxY = Math.max(maxY, myY);
        }
        return maxY;
    * */
}
