package Presentation.Ui_Views;

import Presentation.Manager.MainFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;

import static Presentation.Dictionary_login.*;

public class TempFreePianoUI extends JPanel {
    private JButton profile = new JButton(PROFILE_BUTTON);
    private MainFrame mainFrame;

    public TempFreePianoUI(final MainFrame mainFrame) {
        super();
        this.mainFrame=mainFrame;
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());

        JPanel userButtons = new JPanel();
        userButtons.setLayout(new BoxLayout(userButtons, BoxLayout.Y_AXIS));

        userButtons.add(Box.createRigidArea(new Dimension(10, 15)));

        userButtons.add(Box.createRigidArea(new Dimension(10, 45)));
        profile.setActionCommand(PROFILE_BUTTON);
        profile.setAlignmentX(0.5f);
        profile.setBorder(new EmptyBorder(12,120,12,120));

        userButtons.add(profile);
        this.add(userButtons);
        this.setSize(600, 400);

        this.revalidate();
        this.repaint();
        this.setVisible(true);

    }

    public void registerController(ActionListener listener) {
        profile.addActionListener(listener);
    }
}
