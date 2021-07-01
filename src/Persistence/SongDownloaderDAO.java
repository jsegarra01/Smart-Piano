package Persistence;

import Persistence.SQL.Csv.SongCsvDAO;

import java.io.IOException;

public interface SongDownloaderDAO {
   SongCsvDAO songCsv = new SongCsvDAO();

   String downloadFile(String fileURL, String saveDir) throws IOException;

   boolean downloadWebPage(String webpage);

   void downloadAllSongsScrapping(String webpage);
}
