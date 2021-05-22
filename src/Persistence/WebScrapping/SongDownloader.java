package Persistence.WebScrapping;
import Persistence.SongDownloaderDAO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

    /**
     * Downloads a file from a specified URL ONLY FOR HTTP servers.
     * @param fileURL HTTP URL of the file to be downloaded
     * @param saveDir path of the directory to save the file
     * @throws IOException
     */
    @Override
    public void downloadFile(String fileURL, String saveDir) throws IOException {
            URL url = new URL(fileURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
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
                String saveFilePath = saveDir + File.separator + fileName;

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
        }
    }