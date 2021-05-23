package Business.Entities;

public class ConfigJson {
    private int port;
    private String ipAddress;
    private String name;
    private String username;
    private String password;
    private int scrappingTime;


    public ConfigJson() {
    }

    public int getPort() {
        return port;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScrappingTime() {
        return scrappingTime;
    }
}
