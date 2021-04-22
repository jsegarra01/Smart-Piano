package Persistence.Json;

import Business.Entities.ConfigJson;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;

/**
 * The class ReadConfigJson is in charge of reading the determined file and return the information that has been read.
 * In case it has been able to read it, we will return that information. If not, we will exit the program.
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 22 Apr 2021
 */
public class ReadConfigJson {

    // ConfigJson Class to be set only once (static) in order not to make multiple connections
    private static ConfigJson configJson;

    /**
     * Method that reads the file config.json and stores its information in the configJson attribute
     */
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

    /**
     * Method that will return the configuration of database. If it has not been read yet, it will read it
     * @return Class that stores the configuration of the database
     */
    public static ConfigJson getConfigJson() {
        if(configJson==null){
            readConfigJson();
        }
        return configJson;
    }
}
