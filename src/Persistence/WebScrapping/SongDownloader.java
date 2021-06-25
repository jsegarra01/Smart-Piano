package Persistence.WebScrapping;
import Business.Entities.Song;
import Business.SongManager;
import Persistence.SongDownloaderDAO;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static Business.Entities.WebHandler.getHtmlDocument;
import static Business.Threads.WebScrapping.getSongDownloader;
import static Presentation.Manager.SpotiFrameManager.URLRoute;

/**
 * The following Helper directly downloads any file from the internet only needing the URL. Creates a connection, downloads
 * it into the desired path and closes the connection. In this case, it will mostly be used to download the songs for the
 * Spoti feature of the Piano :D
 * In Java, the classes URL and HttpURLConnection in java.net allow us to download files following these steps:
 *
 * 1) Create a URL object that shall be either A direct link like:
 * https://www.mutopiaproject.org/ftp/BachJS/BWV454/bwv_454/bwv_454.mid
 *
 * Or an indirect Link that does not have the name of the file in it, like:
 * http://myserver.com/download?id=1234
 *
 * 2) Open connection on the URL object.
 * 3) Open the input stream of the opened connection.
 * 4) Create an output stream to save file to disk.
 * 5) Read the input stream and copy its bytes to the output stream until it is empty
 * 6) Close the input stream, the output stream and the connection.
 * For the purpose of specificity and reusability, we will create a general class:
 */
public class SongDownloader implements SongDownloaderDAO {
    private String nameSong = "";
    private SongManager songManager = new SongManager();

    /**
     * Downloads a file from a specified URL ONLY FOR HTTP servers.
     * @param fileURL HTTP URL of the file to be downloaded
     * @param saveDir path of the directory to save the file
     * @throws IOException
     */
    @Override
    public String downloadFile(String fileURL, String saveDir) throws IOException {
            URL url = new URL(fileURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            String saveFilePath = "";
            int responseCode = httpConn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { //200 is OK
                String fileName = "";
                String disposition = httpConn.getHeaderField("Content-Disposition");

                if (disposition != null) {
                    // extracts file name from header field
                    int index = disposition.indexOf("filename=");
                    if (index > 0) {
                        fileName = disposition.substring(index + 10, disposition.length() - 1);
                    }
                }
                else {
                    // extracts file name from URL
                    fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1);
                }

                // opens input stream from the HTTP connection
                InputStream inputStream = httpConn.getInputStream();
                saveFilePath = saveDir + File.separator + fileName;

                // opens an output stream to save into file
                FileOutputStream outputStream = new FileOutputStream(saveFilePath);

                int bytesRead = -1;
                int BUFFER_SIZE = 100000;
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                outputStream.close();
                inputStream.close();
            }

            httpConn.disconnect();
            return saveFilePath;
        }

    @Override
    public void downloadWebPage(String webpage) {
        try {
            // Create URL object
            URL url = new URL(webpage);
            BufferedReader readr = new BufferedReader(new InputStreamReader(url.openStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Files/WebScrappingResults/", "Download.html")));
            String line;
            while ((line = readr.readLine()) != null) {writer.write(line);}
            readr.close();
            writer.close();
        }

        // Exceptions
        catch (IOException mue) {
        }
    }

    @Override
    public void downloadAllSongsScrapping(String webpage) {
        String URL = "";
        int i = 0;
        while (i < 50) {
            if (i == 1) {
                URL = URLRoute;
            }
            else {
                URL = String.format(URLRoute.replace("?", "?startat=%s&"), i);
            }
            try {
                Document doc = getHtmlDocument(URL);
                Elements greatDivs = doc.getElementsByClass("table-bordered result-table");

                for (Element song : greatDivs) {//Elements has inside all the possible divs
                    //The selector span:nth-child(x) looks for the parent of span and chooses the child element in the i position.
                    Elements myEles = song.getAllElements();
                    Element myEle = myEles.get(0);
                    Elements miniEles = myEle.getAllElements();
                    String author2 = miniEles.get(4).text();
                    String piece = miniEles.get(3).text();
                    String downloadURL= miniEles.get(19).getAllElements().get(3).html()
                            .substring(9,miniEles.get(19).getAllElements().get(3).html().length()-15);
                    String recordingDate = miniEles.get(18).text();
                    if(songCsv.getSongByName(piece) == null){
                        String filename = getSongDownloader().downloadFile(downloadURL, "Files/WebScrappingResults");
                        if(author2.substring(0,3).contains("by ")){
                            author2 = author2.substring(3);
                        }
                        songCsv.saveSongWithDate(new Song(piece, author2, 0 /*new MidiHelper().getDuration(filename)/1000000*/, new SimpleDateFormat("yyyy/MM/dd").parse(recordingDate), true, filename, "guest", 0));
                    }
                }
            }
            catch (IOException | ParseException e) {
                System.out.println("ERROR");
            }
            //Extract the divs that have products inside of the previous general Div.
            i++;
        }

    }
}