import Presentation.Manager.MainFrame;

import java.util.ArrayList;

import static Presentation.Manager.MainFrame.setPreMenuUi;

/**
 * The "Main" class will run the program
 */
public class Main {

    public static void main(String[] args) {
        new MainFrame();
        setPreMenuUi();

        //TODO Close the connection to the database
    }
}
 