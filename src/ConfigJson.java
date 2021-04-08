

public class ConfigJson {
    private String port;
    private String ipAddress;
    private String name;
    private String username;
    private String password;
    private String scrappingTime;


    public ConfigJson() {
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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

    public String getScrappingTime() {
        return scrappingTime;
    }

    public void setScrappingTime(String scrappingTime) {
        this.scrappingTime = scrappingTime;
    }
}
