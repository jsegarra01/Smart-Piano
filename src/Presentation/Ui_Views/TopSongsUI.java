package Presentation.Ui_Views;

import Presentation.Manager.MainFrame;

import javax.swing.*;
import java.awt.*;

public class TopSongsUI extends JPanel {
    private MainFrame mainFrame;

    public TopSongsUI(final MainFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setBackground(Color.blue);
    }
}
