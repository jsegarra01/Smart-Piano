package Business.Threads;

import Persistence.Files.ReadJson;
import Persistence.WebScrapping.SongDownloader;

import java.util.Timer;
import java.util.TimerTask;

import static Presentation.DictionaryPiano.URLROUTE;


public class WebScrapping {

    /*
    Defines the attribute that will be in charge of downloading songs from the internet
     */
    private static SongDownloader songDownloader;

    /**
     * Constructor of the WebScrapping class. It initializes the attribute songDownloader at it sets a timer in
     * order to perform the web scrapping depending on the time got by the config.json file
     */
    public WebScrapping () {
        songDownloader = new SongDownloader();
        Timer timer = new Timer("MyTimer");
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (songDownloader.downloadWebPage(URLROUTE)) {
                    songDownloader.downloadAllSongsScrapping(URLROUTE);
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 50, 60000L * ReadJson.getConfigJson().getScrappingTime());
    }

    /**
     * Method that gets the songDownloader. It works as a singleton, as if it is null it call the constructor,
     * thus initializing the attribute
     * @return SongDownloader class that will download the songs from the concrete URL
     */
    public static SongDownloader getSongDownloader () {
        if(songDownloader == null){
            new WebScrapping();
        }
        return songDownloader;
    }

}
