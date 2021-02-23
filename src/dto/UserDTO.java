package dto;/*@author:Dilshan Rajika Withanachchi*/

public class UserDTO {
    private int no;
    private String username;
    private String password;
    private String profile;

    public UserDTO(int no, String username, String password, String profile) {
        this.no = no;
        this.username = username;
        this.password = password;
        this.profile = profile;
    }

    public UserDTO() {
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
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
        return "UserDTO{" +
                "no=" + no +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", profile='" + profile + '\'' +
                '}';
    }
}
