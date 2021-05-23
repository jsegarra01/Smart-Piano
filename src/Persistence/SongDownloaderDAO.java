package Persistence;

import java.io.IOException;

public interface SongDownloaderDAO {

   void downloadFile(String fileURL, String saveDir) throws IOException;

   void downloadWebPage(String webpage);

   void downloadAllSongsScrapping(String webpage);
}
