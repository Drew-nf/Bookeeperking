package LocalDatabase;

public class ClientLogin {
    private String c_username;
    private String c_password;

    public ClientLogin(String c_username, String c_password) {
        this.c_username = c_username;
        this.c_password = c_password;
    }

    public ClientLogin() {
    }

    public String getC_username() {
        return c_username;
    }

    public void setC_username(String c_username) {
        this.c_username = c_username;
    }

    public String getC_password() {
        return c_password;
    }

    public void setC_password(String c_password) {
        this.c_password = c_password;
    }

    @Override
    public String toString() {
        return "ClientLogin{" +
                "c_username='" + c_username + '\'' +
                ", c_password='" + c_password + '\'' +
                '}';
    }
}
