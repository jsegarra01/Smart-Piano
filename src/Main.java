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
        new LoginUserCsvDAO().getByUsername("blayaiai");
        //new SongCsvDAO().songFromCsv("Star Wars Theme","songName");

        //TODO put this ReadConfigJson to the controller.
        //Obtains the information from the readConfigJson().

        //ReadConfigJson.readConfigJson();
    }
}
 