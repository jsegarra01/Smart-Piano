import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadConfigJson {
    private static ConfigJson configJson;

    public static void readConfigJson () {
        Gson gson = new Gson();
        JsonReader reader = null;

        try {
            reader = new JsonReader(new FileReader("Files/config.json"));
            configJson = gson.fromJson(reader, ConfigJson.class);
            //Saving the values of the Json file in the attributes of each of the corresponding classes.
        } catch (FileNotFoundException e) {
            System.out.println("\nError opening the config.json file");
            System.exit(1);
        }

        System.out.println("\nThe file config.json was read correctly");

        try {
            reader.close(); //Closing the reader.

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ConfigJson getConfigJson() {
        return configJson;
    }
}
