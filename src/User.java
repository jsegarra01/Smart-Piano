public class User {
    private String userName;
    private String mail;
    private String password;

    public User(String userName, String mail, String password){
        this.userName = userName;
        this.mail = mail;
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }
}
