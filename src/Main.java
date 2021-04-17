import Presentation.Manager.MainFrame;

import static Presentation.Manager.MainFrame.setPreMenuUi;

/**
 * The "Main" class will run the program
 */
public class Main {

    public static void main(String[] args) {
        new MainFrame();
        setPreMenuUi();

        //TODO put this ReadConfigJson to the controller.
        //Obtains the information from the readConfigJson().
        //ReadConfigJson.readConfigJson();
    }
}
 