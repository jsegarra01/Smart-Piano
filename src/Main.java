import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The "Main" class will run the program
 */
public class Main {

    public static void main(String[] args) {
        //PreMenuUI preMenuUI = new PreMenuUI();
        //ProfileUI profileUI = new ProfileUI();
        //LogInUI logInUI = new LogInUI();
        //SignUpUI signUpUI = new SignUpUI();

        //TODO put this ReadConfigJson to the controller.
        //Obtains the information from the readConfigJson().

        Connection connection = ConnectSQL.getInstance();
        User user = new LoginUserCsvDAO().getByUsername("blayaiai");
        Song song = new SongCsvDAO().getSongByID(1);
        ArrayList<Playlist> playlists = new PlaylistCsvDAO().getPlaylistByUser("blayaiai");
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        System.out.println("hola");
        //ReadConfigJson.readConfigJson();
    }
}
 