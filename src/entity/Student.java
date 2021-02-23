package entity;/*@author:Dilshan Rajika Withanachchi*/

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Student implements SuperEntity {
    @Id
    private String student_id;
    private String student_name;
    private String address;
    private String contact;
    private String dob;
    private String gender;
    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL)
    private List<Registration>registrations;

    public Student(String student_id, String student_name, String address, String contact, String dob, String gender) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.address = address;
        this.contact = contact;
        this.dob = dob;
        this.gender = gender;
    }

    public Student() {
    }

    public Student(String student_id, String student_name, String address, String contact, String dob, String gender, List<Registration> registrations) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.address = address;
        this.contact = contact;
        this.dob = dob;
        this.gender = gender;
        this.registrations = registrations;
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

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }
}
