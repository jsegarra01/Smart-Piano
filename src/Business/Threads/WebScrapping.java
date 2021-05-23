package Business.Threads;

import Persistence.Json.ReadConfigJson;
import Persistence.WebScrapping.SongDownloader;

import java.util.Timer;
import java.util.TimerTask;

import static Presentation.Manager.SpotiFrameManager.URLRoute;

public class WebScrapping {
    public static SongDownloader songDownloader = new SongDownloader();
    Timer timer = new Timer("MyTimer");
    TimerTask timerTask = new TimerTask() {

        @Override
        public void run() {
            songDownloader.downloadWebPage(URLRoute);
            songDownloader.downloadAllSongsScrapping(URLRoute);
        }
    };

    public WebScrapping () {
        timer.scheduleAtFixedRate(timerTask,50, 60000L * ReadConfigJson.getConfigJson().getScrappingTime());
    }

}
