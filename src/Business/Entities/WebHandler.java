package Business.Entities;



import java.io.IOException;

import Business.BusinessFacadeImp;
import Business.SongManager;
import Persistence.WebScrapping.SongDownloader;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebHandler {

    //Indicate where the file shall be stored
    private final String filePath;
    private final String route;
    private final String OfferFile;
    private static final BusinessFacadeImp businessFacadeImp = new BusinessFacadeImp();
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
    private final String author = "";

    public WebHandler(String filePath, String URLroute, String nameFile, String pagingInffix1){
        this.route = URLroute;
        this.OfferFile = nameFile;
        this.filePath = filePath;
        pagingInffix = pagingInffix1;
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
        Connection.Response response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
        return response.statusCode();
    }

    /**
     * With this method we return a Document type with a complete HTML inside of it (that we will have to parse and get
     * info from! For this, JSoup does the trick.
     * @param url the internet path be used
     * @return complete HTML document
     */
    public static Document getHtmlDocument(String url) throws IOException{
        Document htmlDoc;
        htmlDoc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();

        return htmlDoc;
    }
}