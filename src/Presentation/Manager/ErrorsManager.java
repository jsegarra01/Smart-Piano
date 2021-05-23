package Presentation.Manager;

import javax.swing.*;

/**
 * ErrorsManager
 *
 * The "ErrorsManager" class will manage the different errors and exceptions of our class in order to display when there
 * has been an error in the system.
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 23 May 2021
 *
 */
public class ErrorsManager {
    public static long endTime = 0;
    /**
     * Parametrized constructor for the ErrorsManager class
     */
    public ErrorsManager() {

    }

    /**
     * Checks which error has been obtained, and sends the string message to create dialog depending on it.
     * @param errorNumber int. Displays which error has happened: 0: connection to configJson, 1: user exists,
     *                    2: incorrect data when logging in, 3: song can't be reached, 4: song can't be downloaded,
     *                    5: song doesn't exist
     */
    public void errorFound(int errorNumber) {
        switch (errorNumber) {
            case 0 ->                                     //Connection issue to the configJson and the database
                    createDialog("There has been a problem connecting to the database.", "Connection error");
            case 1 ->                                     //A user already exists with that credential
                    createDialog("Values introduced were not accepted.", "SignUp error");
            case 2 ->                                     //The user doesn't exist
                    createDialog("Incorrect username or password", "LogIn error");
            case 3 ->                                     //The song can't be accessed when searching online
                    createDialog("This song can't be accessed.", "Access error");
            case 4 ->                                     //The song can't be downloaded
                    createDialog("The song can't be downloaded.", "Download error");
            case 5 -> createDialog("No song with that Name or Author! Took " + endTime / 1000 + "s.", "Web error");

            case 6 -> createDialog("You must input something more!", "Search Song Error");
            case 7 -> createDialog("This song already exists in the playlist!", "Song adding Error");
        }
    }

    /**
     * Creates the Dialog to display the error we have had in our program
     * @param phrase String. Sentence which displays with words the problem we have had
     * @param title String. Summarize of the phrase, to see where our error occurred
     */
    private void createDialog(String phrase, String title) {
        JOptionPane.showMessageDialog(null, phrase, title, JOptionPane.ERROR_MESSAGE);
    }
}
