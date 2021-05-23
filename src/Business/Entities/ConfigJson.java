package Business.Entities;

/**
 * ConfigJson
 *
 * The "ConfigJson" class will store the values of the configJson and has the methods to access and set them
 *
 * @author OOPD 20-21 ICE5
 * @version 1.0 21 Apr 2021
 *
 */
public class ConfigJson {
    private int port;
    private String ipAddress;
    private String name;
    private String username;
    private String password;
    private int scrappingTime;

    /**
     * Constructor of the ConfigJson
     */
    public ConfigJson() {
    }

    /**
     * Gets the port
     * @return The port
     */
    public int getPort() {
        return port;
    }

    /**
     * Gets the IP address
     * @return The IP address
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Gets the name
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name
     * @param name The desired name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the username
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username
     * @param username The desired username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password
     * @return The password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password
     * @param password The desired password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public int getScrappingTime() {
        return scrappingTime;
    }
}
