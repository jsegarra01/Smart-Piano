package Business.Threads;

import Persistence.Files.ReadJson;
import Persistence.WebScrapping.SongDownloader;

import java.util.Timer;
import java.util.TimerTask;

import static Presentation.Manager.SpotiFrameManager.URLRoute;

public class WebScrapping {
    private static final SongDownloader songDownloader = new SongDownloader();

    public WebScrapping () {
        Timer timer = new Timer("MyTimer");
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                songDownloader.downloadWebPage(URLRoute);
                songDownloader.downloadAllSongsScrapping(URLRoute);
            }
        };
        timer.scheduleAtFixedRate(timerTask,50, 60000L * ReadJson.getConfigJson().getScrappingTime());
    }

    public static SongDownloader getSongDownloader () {
        return songDownloader;
    }

}
