package Business.Entities;



import java.io.IOException;
import java.net.HttpURLConnection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Business.SongManager;
import Persistence.WebScrapping.SongDownloader;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebHandler {

    //Indicate where the file shall be stored
    private  String filePath;
    private  String route;
    private  String OfferFile;
    //This suffix will be used to change the pages of the web and go through all of them. To do this, we leave a space
    //for a parameter.
    /*
    * https://www.mutopiaproject.org/cgibin/make-table.cgi?Instrument=Piano
    * If we go to any page:
    * https://www.mutopiaproject.org/cgibin/make-table.cgi?startat=10&Instrument=Piano
    * */
    private static String pagingInffix; //https://www.mutopiaproject.org/cgibin/make-table.cgi?startat=10&Instrument=Piano
    private static long initTime;
    private static final SongManager manager = new SongManager();
    private static final SongDownloader songDownloader = new SongDownloader();
    private String author = "";

    public WebHandler(String filePath, String URLroute, String nameFile, String pagingInffix){
        this.route = URLroute;
        this.OfferFile = nameFile;
        this.filePath = filePath;
        this.pagingInffix = pagingInffix;
    }

    public void doStuff(String songName, String songAuthor){
        initTime = System.currentTimeMillis();
        try {
            if (getStatusConnectionCode(this.route) == HttpURLConnection.HTTP_OK) {//OK connection!
                searchSong(this.route, songName, songAuthor, this.filePath);
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "The webpage couldn't be loaded!\n The status that the webpage " +
                        "the page returns is: " + getStatusConnectionCode(this.route));
            }
        } catch (IOException e) {

        }
    }

    private void searchSong(String url, String songName, String songAuthor, String filePath){
        initTime = System.currentTimeMillis();

        String newSong = readPage(url, songName, songAuthor);
        if (!newSong.equals("")) {
            //Download the song!
            try {
                songDownloader.downloadFile(newSong, filePath);
                manager.saveSong(new Song(songName,author.substring(3),5,true,filePath + "/" + songName, "Guest"));
                manager.setSongs();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else{
            long endTime = System.currentTimeMillis() - initTime;
            JOptionPane.showMessageDialog(new JFrame(), " No song with that Name or Author! Took " + endTime/1000 + "s.");
        }
    }

    /**
     * Reads the parametrized webpage and returns the filtered and processed document
     * @param stringUrl
     * @param songName
     * @param songAuthor
     * @return
     */
    private String readPage(String stringUrl, String songName, String songAuthor) {
        String URL = "";
        Boolean done = false;
        int i = 0;
        /*
         * This web returns repeated content if we try to read an index of an non existing page. Sometimes if there are only 2 pages
         * and we try to reed the third, it will show again page 2! In this case, if the page doesn't exist, it will show:
         * "Sorry, no matches were found for your search criteria"
         * We will read "until" it shows the latter message.
         */
        //Extract the Div that has all the products' contents.
        do{
            if (i < 50) {
                if (i == 1) {
                    URL = stringUrl;
                }
                else {
                    URL = String.format(stringUrl.replace("?", pagingInffix), i);
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
                        if(author2.contains(songAuthor) && piece.contains(songName)){
                            author = miniEles.get(4).text();
                            done = true;
                            URL = downloadURL;
                        }
                        else{
                            URL = "";
                        }
                    }
                }
                catch (IOException e) {
                    JOptionPane.showMessageDialog(new JFrame(), "There was an error whilst scrapping the web, try later...");
                }

                i++;
            }
            else {
                done = true;
            }
        }while(!done);

        return URL;
    }

    /**
     * We first have to check the Status Code of the connection, hereby I leave some of the most important ones in case
     * the worst happens when we try a petition:
     * 		200 OK			        300 Multiple Choices
     * 		301 Moved Permanently	305 Use Proxy
     * 		400 Bad Request		    403 Forbidden
     * 		404 Not Found		    500 Internal Server Error
     * 		502 Bad Gateway		    503 Service Unavailable
     * @param url to be used
     * @return Status Code in an integer type
     */
    private int getStatusConnectionCode(String url) throws IOException{
        Connection.Response response = Jsoup.connect(url).
                                            userAgent("Mozilla/5.0").
                                                timeout(100000).
                                                    ignoreHttpErrors(true).
                                                        execute();
        return response.statusCode();
    }

    /**
     * With this method we return a Document type with a complete HTML inside of it (that we will have to parse and get
     * info from! For this, JSoup does the trick.
     * @param url the internet path be used
     * @return complete HTML document
     */
    public static Document getHtmlDocument(String url) throws IOException{
        Document htmlDoc = new Document(url);
        htmlDoc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();

        return htmlDoc;
    }
}