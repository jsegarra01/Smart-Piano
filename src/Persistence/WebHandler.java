package Persistence;



import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * The class WebHandler is in charge of getting the HTML from the determined URL. It is used in order to
 * perform the Web Scrapping from the webpage
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 28 June 2021
 */

public class WebHandler {
    /**
     * Method that returns a Document type with a complete HTML inside of it (that we will have to parse and get
     * info from. For this, JSoup does the trick.
     * @param url Defines the internet path to be used
     * @return Document that stores the complete HTML
     */
    public static Document getHtmlDocument(String url) throws IOException{
        Document htmlDoc;
        htmlDoc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
        return htmlDoc;
    }
}