package Business.Entities;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class webHandler {

    //Indicate where the file shall be stored
    private static String filePath;
    private static String route;
    private static String OfferFile;
    //This suffix will be used to change the pages of the web and go through all of them. To do this, we leave a space
    //for a parameter.
    /*
    * https://www.mutopiaproject.org/cgibin/make-table.cgi?Instrument=Piano
    * If we go to any page:
    * https://www.mutopiaproject.org/cgibin/make-table.cgi?startat=10&Instrument=Piano
    * */
    private static String pagingInffix; //https://www.mutopiaproject.org/cgibin/make-table.cgi?startat=10&Instrument=Piano
    private static long initTime;

    public webHandler(String filePath, String URLroute, String nameFile, String pagingInffix){
        this.route = URLroute;
        this.OfferFile = nameFile;
        this.filePath = filePath;
        this.pagingInffix = pagingInffix;
    }
    public void doStuff(String songName, String songAuthor){
        initTime = System.currentTimeMillis();
        //Check the damn Status Code of the webpage (200 is OK)
        if(getStatusConnectionCode(this.route) == HttpURLConnection.HTTP_OK){//OK connection!
            searchSong(this.route, songName, songAuthor, this.filePath);
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "The webpage couldn't be loaded!\n The status that the webpage " +
                    "the page returns is: " + getStatusConnectionCode(this.route));
        }
        System.exit(0);
    }

    public static void searchSong(String url, String songName, String songAuthor, String filePath){
        initTime = System.currentTimeMillis();

        String newSong = readPage(url, songName, songAuthor);
        if (newSong != ""){
            //Download the song!
            try {
                songDownloader.downloadFile(newSong, filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            long endTime = System.currentTimeMillis() - initTime;
            JOptionPane.showMessageDialog(new JFrame(), endTime + " No song with that Name or Author!.");
        }
    }


    /**
     * Reads the parametrized webpage and returns the filtered and processed document
     * @param stringUrl
     * @param songName
     * @param songAuthor
     * @return
     */
    public static String readPage(String stringUrl, String songName, String songAuthor) {
        String URL;
        Boolean done = false;
        String toreturn = null;
        int i = 0;
        /*
         * This web returns repeated content if we try to read an index of an non existing page. Sometimes if there are only 2 pages
         * and we try to reed the third, it will show again page 2! In this case, if the page doesn't exist, it will show:
         * "Sorry, no matches were found for your search criteria"
         * We will read "until" it shows the latter message.
         */
        //Extract the Div that has all the products' contents.
        do{
            if (i == 1) {
                URL = stringUrl;
            } else {
                URL = String.format(stringUrl.replace("?", pagingInffix), i);
                System.out.println(URL);
            }
            Document doc = getHtmlDocument(URL);
            //if (!doc.hasClass("table-bordered result-table")) { //This crazy loop goes through ALL the damn pages! 880 of them!
            if (i < 50) {//This... well, just leaves 20 pages to look into! Quite better, for now xdxd
                Elements greatDivs = doc.getElementsByClass("table-bordered result-table");
                //Extract the divs that have products inside of the previous general Div.
                for (Element song : greatDivs) {//Elements has inside all the possible divs
                    //The selector span:nth-child(x) looks for the parent of span and chooses the child element in the i position.
                    Elements myEles = song.getAllElements();
                    Element myEle = myEles.get(0);
                    Elements miniEles = myEle.getAllElements();
                    String author = miniEles.get(4).text();
                    String piece = miniEles.get(3).text();
                    String downloadURL= miniEles.get(19).getAllElements().get(3).html()
                            .substring(9,miniEles.get(19).getAllElements().get(3).html().length()-15);
                    if(author.contains(songAuthor) && piece.contains(songName)){
                        done = true;
                        System.out.println("Found the piece " + piece + author);
                        URL = downloadURL;
                        break;
                    }
                }
                i++;
            } else {
                done = true;
            }
        }while(!done);
        System.out.println(URL);
        return URL;
    }

    /**
     * Writes the complete text into the file in the specified filePath
     * @param myFile
     * @param processedText
     * @return
     */
    public static Boolean writeCompleteFile(String processedText, File myFile){
        FileWriter writer = null;
        Boolean status = false;
        try{
            writer = new FileWriter(myFile);
            writer.write(processedText);
            status = true;
        }catch(FileNotFoundException e){
            System.out.println("The specified file does not exist OR the file is in another folder");
            JOptionPane.showMessageDialog(new JFrame(), "There was an error during the creation of the file"
                    + "\nIn the following Path: "+filePath);
        }catch(IOException e){
            JOptionPane.showMessageDialog(new JFrame(), "There was an error during the creation of the file"
                    + "\nIn the following Path: "+filePath);
        }finally{
            if (writer != null){
                try {
                    writer.close();
                } catch (IOException e) {
                    //Esto es un cachondeo xd
                    e.printStackTrace();
                }
            }
        }
        return status;
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
    public static int getStatusConnectionCode(String url) {

        Connection.Response response = null;

        try {
            response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
        } catch (IOException ex) {
            System.out.println("Exception while trying to get the Code: " + ex.getMessage());
        }
        return response.statusCode();
    }

    /**
     * With this method we return a Document type with a complete HTML inside of it (that we will have to parse and get
     * info from! For this, JSoup does the trick.
     * @param url the internet path be used
     * @return complete HTML document
     */
    public static Document getHtmlDocument(String url) {
		/*We could go all the way and read line by line, you'll have to excuse me, but I prefer to use a Document for
		this*/
        Document htmlDoc = new Document(url);
        try {
            htmlDoc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
        }catch(IOException ex) {
            System.out.println("There has been an error, review the Exception Code: " + ex.getMessage());
        }
        return htmlDoc;
    }
}