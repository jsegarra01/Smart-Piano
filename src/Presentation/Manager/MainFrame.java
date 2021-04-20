package Presentation.Manager;

import Presentation.Ui_Views.*;

import javax.swing.*;

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
                LoginUIManager loginUIManager = new LoginUIManager();
                signUpUI.registerController(loginUIManager);
            }
        });
    }

    public static void setPianoTilesSelector() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PianoTilesUISelector pianoTilesUISelector = new PianoTilesUISelector(mainFrame);
                PianoTilesUIManager pianoTilesUIManager = new PianoTilesUIManager();
                pianoTilesUISelector.registerController(pianoTilesUIManager);
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
