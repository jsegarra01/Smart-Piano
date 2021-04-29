package Presentation.Ui_Views;

import Presentation.Manager.MainFrame;

import javax.swing.*;
import java.awt.*;


//import static Presentation.DictionaryPiano.*;

/**
 * SpotiUI
 *
 * The "SpotiUI" class will allow us to choose the different songs we have available to play
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 24 Apr 2021
 *
 */
public class SpotiUI extends JPanel {
    /*
     private JLabel pianoText = new JLabel(SMART_PIANO_TEXT);
     private JLabel logInText = new JLabel(LOG_IN_TEXT);

     private static JTextField usernameTextField = new JTextField();
     private static JPasswordField password = new JPasswordField();
     private JButton back = new JButton(BACK_BUTTON);
     private JButton done = new JButton(DONE_BUTTON);*/
    private MainFrame mainFrame;

    /**
     * Constructor for the SpotiUI, you need to send the mainframe context and will create a card layout
     * @param mainFrame context necessary to create the card layout
     */
    public SpotiUI(final MainFrame mainFrame) {
        super();
        this.mainFrame=mainFrame;
        initialize();
    }

    /**
     * The initialize function that creates the card layout for the SpotiUI
     */
    private void initialize() {
        setLayout(new BorderLayout());
    }
}