import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PreMenuUI extends JFrame{

    private JLabel pianoText = new JLabel("SMART PIANO");

    private JButton logIn = new JButton("LOG IN");
    private JButton signUp = new JButton("SIGN UP");
    private JButton guest = new JButton("ENTER AS GUEST");

    public PreMenuUI() {


        setTitle("SMART PIANO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        setLayout(new BorderLayout());

        JPanel userButtons = new JPanel();
        userButtons.setLayout(new BoxLayout(userButtons, BoxLayout.Y_AXIS));

        userButtons.add(Box.createRigidArea(new Dimension(10, 10)));

        Font currentFont = pianoText.getFont();
        Font newFont = currentFont.deriveFont(currentFont.getSize() * 2.0F);
        pianoText.setFont(newFont);
        pianoText.setAlignmentX(0.5f);
        pianoText.setForeground(Color.WHITE);
        userButtons.add(pianoText);

        userButtons.add(Box.createRigidArea(new Dimension(10, 25)));

        logIn.setAlignmentX(0.5f);
        logIn.setBorder(new EmptyBorder(10,100,10,100));
        userButtons.add(logIn);

        userButtons.add(Box.createRigidArea(new Dimension(10, 20)));

        signUp.setAlignmentX(0.5f);
        signUp.setBorder(new EmptyBorder(10,96,10,96));
        userButtons.add(signUp);

        userButtons.add(Box.createRigidArea(new Dimension(10, 20)));

        guest.setAlignmentX(0.5f);
        guest.setBorder(new EmptyBorder(10,68,10,68));
        userButtons.add(guest);

        userButtons.setBackground(Color.getHSBColor(0, 0, 0.1f));

        JPanel background = new JPanel();
        background.setLayout(new BorderLayout());

        background.add(userButtons, BorderLayout.CENTER);
        setBackground(Color.BLACK);


        JPanel pN = new JPanel();
        pN.setBackground(Color.getHSBColor(0, 0, 0.2f));
        add(pN, BorderLayout.NORTH);

        JPanel pS = new JPanel();
        pS.setBackground(Color.getHSBColor(0, 0, 0.2f));
        add(pS, BorderLayout.SOUTH);

        JPanel pE = new JPanel();
        pE.setBackground(Color.getHSBColor(0, 0, 0.2f));
        add(pE, BorderLayout.EAST);

        JPanel pW = new JPanel();
        pW.setBackground(Color.getHSBColor(0, 0, 0.2f));
        add(pW, BorderLayout.WEST);

        add(background);
        pack();

        setSize(400, 400);
        setVisible(true);
    }
}
