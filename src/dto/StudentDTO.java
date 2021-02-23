package dto;/*@author:Dilshan Rajika Withanachchi*/

public class StudentDTO {
    private int no;
    private String student_id;
    private String student_name;
    private String address;
    private String contact;
    private String dob;
    private String gender;

    public StudentDTO() {
    }

    public StudentDTO(int no, String student_id, String student_name, String address, String contact, String dob, String gender) {
        this.no = no;
        this.student_id = student_id;
        this.student_name = student_name;
        this.address = address;
        this.contact = contact;
        this.dob = dob;
        this.gender = gender;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "no=" + no +
                ", student_id='" + student_id + '\'' +
                ", student_name='" + student_name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", dob='" + dob + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
