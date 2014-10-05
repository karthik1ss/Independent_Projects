package Persistence;

import java.io.Serializable;

public class Login implements Serializable,Comparable<Login>
{
    private String username;
    private String password;
    private String email;

    public Login()
    {}

    public Login(String username,String password)
    {
        this.username=username;
        this.password=password;
    }

    public Login(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int compareTo(Login login) {
        int lastCmp = username.compareTo(login.password);
        return (lastCmp != 0 ? lastCmp :
                username.compareTo(login.password));
    }

    

}
