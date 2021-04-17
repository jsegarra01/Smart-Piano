package Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PreMenuUIManager implements ActionListener {
    // Attribute storing the view, for managing purposes. We will modify
    //  it after we get notified that a button has been pressed.
    private PreMenuUI view;

    // Parametrized constructor, receiving the view.
    public PreMenuUIManager(PreMenuUI view) {
        this.view = view;
    }

    // Method that will be called every time a button is pressed, overriden
    //  from the interface to provide an implementation.
    @Override
    public void actionPerformed(ActionEvent e) {
       // We distinguish between our buttons.
        switch (e.getActionCommand()) {
            case Dictionary_login.LOG_IN_BUTTON:
                LogInUI logInUI = new LogInUI(view);
                break;
            /*case MainFrame.BTN_ERASE:
                view.eraseCanvas();
                break;
            case MainFrame.BTN_TEXT_INC:
                view.increaseTextSize();
                break;
            case MainFrame.BTN_TEXT_DEC:
                view.decreaseTextSize();
                break;*/
        }
    }
}
