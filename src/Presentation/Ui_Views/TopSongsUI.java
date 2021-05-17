package Presentation.Ui_Views;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class TopSongsUI extends JPanel {
    private final LinkedList<Float> numPlayed = new LinkedList<Float>();

    public TopSongsUI() {
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(Color.white);
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
