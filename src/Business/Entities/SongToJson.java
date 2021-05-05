package Business.Entities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SongToJson {
    /**
     * Writes the new rapper that the user has introduced in the competition Json file.
     * @param rapper The rapper registered by the user.
     */
    public static void writeJSONsong (SongRecorded songRecorded, String songName) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Writer writer = null;

        try {
            writer = Files.newBufferedWriter(Paths.get("song/" + songName + ".json"));
            //Defining to which file we will write.
        } catch (IOException e) {
            e.printStackTrace();
        }

        gson.toJson(songRecorded, writer); //Writing the content of readCompetition in the file.

        try {
            writer.close(); //Closing the writer.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}