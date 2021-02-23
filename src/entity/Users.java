package entity;/*@author:Dilshan Rajika Withanachchi*/

import javax.persistence.*;

@Entity
public class Users implements SuperEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String username;
    private String password;
    private String profile;

    public Users() {
    }

    public Users(String username, String password, String profile) {
        this.username = username;
        this.password = password;
        this.profile = profile;
    }

    public Users(int id, String username, String password, String profile) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.profile = profile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", profile='" + profile + '\'' +
                '}';
    }
}
