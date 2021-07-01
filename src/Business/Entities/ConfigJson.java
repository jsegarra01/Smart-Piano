package Business.Entities;

/**
 * ConfigJson
 *
 * The "ConfigJson" class will store the values of the configJson and has the methods to access and set them
 *
 * @author OOPD 20-21 ICE5
 * @version 2.0 28 June 2021
 *
 */
public class ConfigJson {

    /*
    Defines the port of the connection
     */
    private int port;

    /*
    Defines the ip address of the connection
     */
    private String ipAddress;

    /*
    Defines the name of the database
     */
    private String name;

    /*
    Defines the username accessing to the database
     */
    private String username;

    /*
    Defines the password of the username that is accessing to the database
     */
    private String password;

    /*
    Defines the scrapping time to do the webScrapping
     */
    private int scrappingTime;

    /**
     * Constructor of the ConfigJson
     */
    public ConfigJson() {
    }

    /**
     * Method that gets the port
     * @return Int that stores the port of the connection
     */
    public int getPort() {
        return port;
    }

    /**
     * Method that gets the IP address
     * @return String that stores the IP address
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Method that gets the name of the database
     * @return String that stores the name of the database
     */
    public String getName() {
        return name;
    }

    /**
     * Method that gets the username that is accessing the database
     * @return String that stores the username that is accessing the database
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method that gets the password of the user accessing the database
     * @return String that stores the password of the user that is accessing the database
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method that gets the scrapping time
     * @return Int returning the time of scrapping
     */
    public int getScrappingTime() {
        return scrappingTime;
    }
}
