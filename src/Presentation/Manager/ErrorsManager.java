package Presentation.Manager;

import javax.swing.*;

/**
 * ErrorsManager
 *
 * The "ErrorsManager" class will manage the different errors and exceptions of our class in order to display when there
 * has been an error in the system.
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 28 June 2021
 *
 */
public class ErrorsManager {
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
            case 5 ->                                     //There's no name or Author matching the error
                    createDialog("Nothing could be found.", "Web error");
            case 6 ->                                     //There's nothing to search
                    createDialog("You must input something more!", "Search Song Error");
            case 7 ->                                     //The song already exists
                    createDialog("This song already exists in the playlist!", "Song adding Error");
            case 8 ->                                     //Midi exception
                    createDialog("It was not possible to connect to the midi!", "Midi Error");
            case 9 ->                                     //Update problem
                    createDialog("There's a problem updating the data.", "Problem Error");
            case 10 ->                                     //Update problem
                    createDialog("The webpage can't be accessed", "Webpage Error");
            case 11 ->
                    createDialog("This key is already assigned!", "Modify keys error");
            case 12 ->
                    createDialog("The input is not correct!", "Create playlist error");
            case 13 ->
                    createDialog("The username cannot be empty!", "Sign Up error");
            case 14 ->
                    createDialog("The email cannot be empty!", "Sign Up error");
            case 15 ->
                    createDialog("The password must be at least 8 characters long!", "Sing Up error");
            case 16 ->
                    createDialog("The password must contain an upper case, lower case and numbers!",
                            "Sign Up error");
            case 17 ->
                    createDialog("Both passwords don't match!", "Sign Up error");
            case 18 ->
                    createDialog("The input cannot be empty!", "Song recording error");
            case 19 ->
                    createDialog("There was an error adding the song to the playlist!", "Song adding error");
            case 20 ->
                    createDialog("There was an error while adding the playlist", "Create playlist error");
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
