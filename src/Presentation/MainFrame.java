package Presentation;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public static JFrame mainFrame;

    public MainFrame() {
        mainFrame = new JFrame();
        mainFrame.setVisible(true);
    }

    public void configureMainView() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PreMenuUI preMenuUI = new PreMenuUI(mainFrame);
                PreMenuUIManager menuUIManager = new PreMenuUIManager(preMenuUI);
                preMenuUI.registerController(menuUIManager);
            }
        });
    }
    public static void setLoginUi() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LoginUI loginUI = new LoginUI(mainFrame);
                LoginUIManager loginUIManager = new LoginUIManager(loginUI);
                loginUI.registerController(loginUIManager);
            }
        });
        System.out.println("s");
    }

}
