package entity;
/*@author:Dilshan Rajika Withanachchi*/
import com.sun.istack.NotNull;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
@Entity
public class Course implements SuperEntity  {
    @Id
private String course_id;
private String course_name;
private String duration;
private double course_fee;
@OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
private List<Registration>registrations;

    public Course(String course_id, String course_name, String duration, double course_fee) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.duration = duration;
        this.course_fee = course_fee;
    }

    public Course() {
    }

    public Course(String course_id, String course_name, String duration, double course_fee, List<Registration> registrations) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.duration = duration;
        this.course_fee = course_fee;
        this.registrations = registrations;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getCourse_fee() {
        return course_fee;
    }

    public void setCourse_fee(double course_fee) {
        this.course_fee = course_fee;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }
}
