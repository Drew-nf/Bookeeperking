package LocalDatabase;

public class Login {
    private String username;
    private String password;
    private Boolean is_acc;

    public Login(String username, String password,Boolean is_acc) {
        this.username = username;
        this.password = password;
        this.is_acc = is_acc;
    }

    public Login() {
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

    public Boolean getIs_acc(){return is_acc;}

    public void setIs_acc(Boolean is_acc){this.is_acc = is_acc;}

    @Override
    public String toString() {
        return "ClientLogin{" +
                "c_username='" + username + '\'' +
                ", c_password='" + password + '\'' +
                '}';
    }
}
