package Business.Entities;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.print.Doc;
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
    private static String routeLeft;
    private static String routeRight;
    private static String OfferFile;
    //This suffix will be used to change the pages of the web and go through all of them. To do this, we leave a space
    //for a parameter.
    /*
    * https://www.mutopiaproject.org/cgibin/make-table.cgi?Instrument=Piano
    * If we go to any page:
    * https://www.mutopiaproject.org/cgibin/make-table.cgi?startat=10&Instrument=Piano
    * */
    private static String pagingInffix= "-startat=%s&"; //https://www.mutopiaproject.org/cgibin/make-table.cgi?startat=10&Instrument=Piano
    private static long initTime;

    public webHandler(String filePath, String URLroute, String OfferFile){
        //this.routeLeft = URLroute.replace("?", pagingInffix);
        //this.routeLeft = URLroute.substring(0,53);
        //this.routeRight = URLroute.substring(53,filePath.length()-1);
        this.route = URLroute;
        //this.filePath = System.getProperty("user.dir")+"/";
        this.OfferFile = OfferFile;
        //this.OfferFile = "things.txt";
        this.filePath = filePath;
        //this.route = "https://www.mutopiaproject.org/cgibin/make-table.cgi?Instrument=Piano";
    }
    public void doStuff(){
        initTime = System.currentTimeMillis();
        //Check the damn Status Code of the webpage
        //int code = getStatusConnectionCode(this.routeLeft + this.routeRight);
        int code = getStatusConnectionCode(this.route);
        if(code == 200){//OK connection!
            createOrCheckFile(this.route, this.filePath+this.OfferFile);
        }else{
            JOptionPane.showMessageDialog(new JFrame(), "The webpage couldn't be loaded!\n The status that the webpage " +
                    "the page returns is: "+code);
        }
        System.exit(0);
    }



    /**
     * The processed text and the file are processe together and compared. In case there are differences, these are
     * included in a String Array that will be used to trigger a "Hey, there are differences flag"
     * @param songName
     * @param processedText
     * @return array of differences: ArrayList<String[]>
     */
    public static boolean comparingSongText(String songName, String processedText){
        FileReader myFileReader;
        BufferedReader bufferedReader = null;
        boolean gotIt = false;/*
        try{
            //get the song from inside of the text!
            //There will be the need to catch exceptions from the reader!

        }catch(FileNotFoundException e){
            e.getMessage();
        }catch (IOException e) {
            e.getMessage();
        }finally{
            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }*/
        return gotIt;
    }



    public static void createOrCheckFile(String url, String fileName){
        File myFile = new File(fileName);
        String processedText = readPage(url);

        if (!myFile.exists()){//" .exists()" is a method from the File type in Java, it just asks for it in the proposed directory
            if (writeCompleteFile(processedText, myFile)){
                //do stuff if the processed file is correct and has been created successfully.
            }
        }else{
            //boolean gotIt = comparingSongText(songName, processedText);  This method has yet to be added, but shall be
            //used HERE!
            /*if (gotIt){
                //doStuff for now not working
            }else{
                long endTime = System.currentTimeMillis() - initTime;
                JOptionPane.showMessageDialog(new JFrame(), endTime + " The asked for song does not exist");
            }*/
        }
    }



    /**
     * Reads the parametrized webpage and returns the filtered and processed document
     * @param stringUrl
     * @return
     */
    public static String readPage(String stringUrl){
        String  processedDocument, toSaveDocument;
        toSaveDocument = new String();
        int i = 1;
        String URL;
        Boolean done = false;
        /*
         * This web returns repeated content if we try to read an index of an non existing page. Sometimes if there are only 2 pages
         * and we try to reed the third, it will show again page 2! In this case, if the page doesn't exist, it will show:
         * "Sorry, no matches were found for your search criteria"
         * We will read "until" it shows the latter message.
         */
        do{
            processedDocument = new String();
            if(i == 1){
                URL = stringUrl;
            }else{
                URL = String.format(stringUrl.replace("?","-startat=%s&"), i);
            }
            Document doc = getHtmlDocument(URL);
            System.out.println(doc);
                //Extract the Div that has all the products' contents.
            Element greatDiv = doc.getElementById("");
                //Extract the divs that have products inside of the previous general Div.
            Elements songs = greatDiv.getElementsByClass("");//Elements has inside all the possible divs
            for (Element song : songs){
                //The selector span:nth-child(x) looks for the parent of span and chooses the child element in the i position.
                processedDocument += "\n" + song.select("AQUI VA {aun... xd} TODO LO RELATIVO A LA BUSQUEDA DEL NOMBRE").
                        text() + " -- " + song.getElementsByTag("a").attr("href");
            }
            if(!processedDocument.contains("Sorry, no matches were found for your search criteria")){
                toSaveDocument += processedDocument;
                i++;
            }else{
                done = true;
            }
        }while(!done);
        return toSaveDocument;
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
