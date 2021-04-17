import Presentation.*;

import javax.swing.*;

/**
 * The "Main" class will run the program
 */
public class Main {

    public static void main(String[] args) {
        //PreMenuUI preMenuUI = new PreMenuUI();
        //ProfileUI profileUI = new ProfileUI();
        //LogInUI logInUI = new LogInUI();
        //SignUpUI signUpUI = new SignUpUI();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            PreMenuUI MainFrame = new PreMenuUI();
            PreMenuUIManager menuUIManager = new PreMenuUIManager(MainFrame);
            MainFrame.registerController(menuUIManager);
            }
        });
        //TODO put this ReadConfigJson to the controller.
        //Obtains the information from the readConfigJson().
        //ReadConfigJson.readConfigJson();
    }
}
 