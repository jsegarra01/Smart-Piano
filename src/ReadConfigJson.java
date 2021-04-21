import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadConfigJson {
    private static ConfigJson configJson;

    private static void readConfigJson () {
        Gson gson = new Gson();

        try {
            JsonReader reader = new JsonReader(new FileReader("Files/config.json"));
            configJson = gson.fromJson(reader, ConfigJson.class);
            reader.close();
            //Saving the values of the Json file in the attributes of each of the corresponding classes.
        } catch (IOException e) {
            System.out.println("\nError opening the config.json file or closing the file");
            System.exit(1);
        }

        System.out.println("\nThe file config.json was read correctly");


    }

    public static ConfigJson getConfigJson() {
        if(configJson==null){
            readConfigJson();
        }
        return configJson;
    }
}
