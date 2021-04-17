package Presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUIManager implements ActionListener {
    // Attribute storing the view, for managing purposes. We will modify
    //  it after we get notified that a button has been pressed.
    private JFrame view;

    // Parametrized constructor, receiving the view.
    public LoginUIManager(JFrame view) {
        this.view = view;

    }

    // Method that will be called every time a button is pressed, overriden
    //  from the interface to provide an implementation.
    @Override
    public void actionPerformed(ActionEvent e) {
        // We distinguish between our buttons.
        switch (e.getActionCommand()) {
            case Dictionary_login.BACK_BUTTON:
                //new PreMenuUI(view);
                break;
            case Dictionary_login.DONE_BUTTON:
                break;
        }
    }
}
