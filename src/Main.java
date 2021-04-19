import com.mysql.cj.log.Log;

import java.util.ArrayList;

/**
 * The "Main" class will run the program
 */
public class Main {

    public static void main(String[] args) {
        ReadConfigJson.readConfigJson();


        /*PreMenuUI preMenuUI = new PreMenuUI();
        ProfileUI profileUI = new ProfileUI();
        LogInUI logInUI = new LogInUI();
        SignUpUI signUpUI = new SignUpUI();

        */
        new LoginUserCsvDAO().save(new LoginUserCsvDAO().getByUsername("blayaiai"));
        ArrayList<Song> songs = new SongCsvDAO().getAllSongs();
        //TODO put this ReadConfigJson to the controller.
        //Obtains the information from the readConfigJson().
        new SongCsvDAO().saveSong(songs.get(0));


        System.out.println("hola");
        //ReadConfigJson.readConfigJson();
    }
}
 