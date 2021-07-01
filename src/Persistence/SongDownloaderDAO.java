package Persistence;

import Persistence.SQL.Csv.SongCsvDAO;

import java.io.IOException;

/**
 * Interface that abstracts the persistence of groups from upper layers.
 *
 * <p>In particular, it follows the Data Access Object design pattern, which is commonly used to abstract persistence
 * implementations with a set of generic operations.
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 28 June 2021
 */
public interface SongDownloaderDAO {
   SongCsvDAO songCsv = new SongCsvDAO();

   /**
    * Downloads a file from a specified URL ONLY FOR HTTP servers.
    * @param fileURL HTTP URL of the file to be downloaded
    * @param saveDir path of the directory to save the file
    * @return String that stores the path of the file
    * @throws IOException IO Exception has happened
    */
   String downloadFile(String fileURL, String saveDir) throws IOException;

   /**
    * Method that downloads the webpage set in the parameter
    * @param webpage Defines the url of the webpage to download the songs
    */
   void downloadWebPage(String webpage);

   /**
    * Method that downloads all the songs from the determined webpage set in the parameter
    * @param webpage Defines the url of the webpage to download the songs
    */
   void downloadAllSongsScrapping(String webpage);
}
