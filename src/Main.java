import Presentation.Manager.MainFrame;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import Business.Entities.webHandler;


/**
 * The "Main" class will run the program
 */
public class Main {

    public static void main(String[] args) {
        String URLRoute = "https://www.mutopiaproject.org/cgibin/make-table.cgi?Instrument=Piano";
        String path = "Files";
        webHandler myWebHandlingTool = new webHandler(path, URLRoute, "result%s.txt", "?startat=%s&");
        myWebHandlingTool.doStuff("Ermuntre dich, mein schwacher Geist", "J. S. Bach"); //page 10 :D
        MainFrame mainframe = new MainFrame();

        mainframe.setSize(1225, 675);
        mainframe.setVisible(true);
        mainframe.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //TODO Close the connection to the database
    }
}
 