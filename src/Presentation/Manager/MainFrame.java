package Presentation.Manager;
/*
 * MainFrame
 *
 * The "MainFrame" class will contain the different methods that are needed for the views of the login
 *
 * Stepan Batllori, Alex Blay, Laura Nuez, Josep Segarra and Sergi Vives
 *
 * Version 21/04/2021
 */

//Imports needed from the dictionary, events and mainframe
import Presentation.Ui_Views.*;

import javax.swing.*;

/**
 * The "MainFrame" class will contain the different methods that are needed for the views of the login
 */
public class MainFrame extends JFrame {
    public static JFrame mainFrame;

    public MainFrame() {
        mainFrame = new JFrame();
        mainFrame.setVisible(true);
    }

    public static void setPreMenuUi() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PreMenuUI preMenuUI = new PreMenuUI(mainFrame);
                PreMenuUIManager menuUIManager = new PreMenuUIManager();
                preMenuUI.registerController(menuUIManager);
            }
        });
    }

    public static void setLoginUi() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginUI loginUI = new LoginUI(mainFrame);
                LoginUIManager loginUIManager = new LoginUIManager();
                loginUI.registerController(loginUIManager);
            }
        });
    }

    public static void setSignUpUi() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SignUpUI signUpUI = new SignUpUI(mainFrame);
                SignUpUiManager signUpUiManager = new SignUpUiManager();
                signUpUI.registerController(signUpUiManager);
            }
        });
    }

    public static void setFreePianoUI() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FreePianoUI freePianoUI = new FreePianoUI(mainFrame);
                FreePianoUIManager freePianoUIManager = new FreePianoUIManager();
                freePianoUI.registerController(freePianoUIManager);
            }
        });
    }

    public static void setProfileUI() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ProfileUI profileUI = new ProfileUI(mainFrame);
                ProfileUIManager profileUIManager = new ProfileUIManager();
                profileUI.registerController(profileUIManager);
            }
        });
    }

}
