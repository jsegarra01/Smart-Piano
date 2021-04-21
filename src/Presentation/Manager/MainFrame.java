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
    public static JFrame mainFrame; //Contains the main view we will modify each time

    /**
     * Parametrized constructor
     */
    public MainFrame() {
        mainFrame = new JFrame();
        mainFrame.setVisible(true);
    }

    /**
     * Static Method that will be called every time the view is changed to PreMenuUI, overriding the mainframe.
     */
    public static void setPreMenuUi() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PreMenuUI preMenuUI = new PreMenuUI(mainFrame);
                PreMenuUIManager menuUIManager = new PreMenuUIManager();
                preMenuUI.registerController(menuUIManager);
            }
        });
    }

    /**
     * Static Method that will be called every time the view is changed to LoginUI, overriding the mainframe.
     */
    public static void setLoginUi() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginUI loginUI = new LoginUI(mainFrame);
                LoginUIManager loginUIManager = new LoginUIManager();
                loginUI.registerController(loginUIManager);
            }
        });
    }

    /**
     * Static Method that will be called every time the view is changed to SignUpUI, overriding the mainframe.
     */
    public static void setSignUpUi() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SignUpUI signUpUI = new SignUpUI(mainFrame);
                SignUpUiManager signUpUiManager = new SignUpUiManager();
                signUpUI.registerController(signUpUiManager);
            }
        });
    }

    /**
     * Static Method that will be called every time the view is changed to ProfileUI, overriding the mainframe.
     */
    public static void setProfileUI() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ProfileUI profileUI = new ProfileUI(mainFrame);
                ProfileUIManager profileUIManager = new ProfileUIManager();
                profileUI.registerController(profileUIManager);
            }
        });
    }

    /**
     * Static Method that will be called every time the view is changed to FreePianoUI, overriding the mainframe.
     */
    public static void setFreePianoUI() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TempFreePianoUI freePianoUI = new TempFreePianoUI(mainFrame);
                TempFreePianoUIManager freePianoUIManager = new TempFreePianoUIManager();
                freePianoUI.registerController(freePianoUIManager);
            }
        });
    }

}
