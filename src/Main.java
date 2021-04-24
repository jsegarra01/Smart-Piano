import Presentation.Manager.MainFrame;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * The "Main" class will run the program
 */
public class Main {

    public static void main(String[] args) {
        MainFrame mainframe = new MainFrame();

        mainframe.setSize(600,600);
        mainframe.setVisible(true);
        mainframe.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //TODO Close the connection to the database
    }
}
 