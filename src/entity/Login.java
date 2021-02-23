package entity;/*@author:Dilshan Rajika Withanachchi*/

import javax.persistence.*;

@Entity
public class Login implements SuperEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no")
    private int no;
    private String user_name;
    private String password;
    private String login_date;
    private String login_time;
    private String login_status;

    public Login() {
    }
    public Login(String user_name, String password, String login_date, String login_time, String login_status) {
        this.user_name = user_name;
        this.password = password;
        this.login_date = login_date;
        this.login_time = login_time;
        this.login_status = login_status;
    }

    public Login(int no, String user_name, String password, String login_date, String login_time, String login_status) {
        this.no = no;
        this.user_name = user_name;
        this.password = password;
        this.login_date = login_date;
        this.login_time = login_time;
        this.login_status = login_status;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin_date() {
        return login_date;
    }

    public void setLogin_date(String login_date) {
        this.login_date = login_date;
    }

    public String getLogin_time() {
        return login_time;
    }

    public void setLogin_time(String login_time) {
        this.login_time = login_time;
    }

    public String getLogin_status() {
        return login_status;
    }

    public void setLogin_status(String login_status) {
        this.login_status = login_status;
    }

    @Override
    public String toString() {
        return "Login{" +
                "no=" + no +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", login_date='" + login_date + '\'' +
                ", login_time='" + login_time + '\'' +
                ", login_status='" + login_status + '\'' +
                '}';
    }
}
