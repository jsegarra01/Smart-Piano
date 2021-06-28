import Business.BusinessFacade;
import Business.BusinessFacadeImp;
import Presentation.Manager.MainFrame;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


/**
 * The "Main" class will run the program
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainframe = new MainFrame(/*myFacade*/);
            mainframe.setSize(1225, 675);
            mainframe.setVisible(true);
            mainframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
        });

    }
}
 