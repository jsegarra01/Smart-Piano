package Business.Entities;



import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebHandler {
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