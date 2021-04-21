package Presentation.Ui_Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;

import static Presentation.Dictionary_login.*;

public class TempFreePianoUI extends JFrame {
    private JButton profile = new JButton(PROFILE_BUTTON);

    public TempFreePianoUI(JFrame frame) {
        frame.getContentPane().removeAll();

        setTitle(SMART_PIANO_TEXT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        setLayout(new BorderLayout());

        JPanel userButtons = new JPanel();
        userButtons.setLayout(new BoxLayout(userButtons, BoxLayout.Y_AXIS));

        userButtons.add(Box.createRigidArea(new Dimension(10, 15)));

        userButtons.add(Box.createRigidArea(new Dimension(10, 45)));
        profile.setActionCommand(PROFILE_BUTTON);
        profile.setAlignmentX(0.5f);
        profile.setBorder(new EmptyBorder(12,120,12,120));

        userButtons.add(profile);
        frame.add(userButtons);
        frame.pack();
        frame.setSize(600, 400);

        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);

    }

    public void registerController(ActionListener listener) {
        profile.addActionListener(listener);
    }
}
