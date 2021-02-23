package entity;/*@author:Dilshan Rajika Withanachchi*/

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Registration implements SuperEntity  {
    @Id
    private String reg_no;
    @Id
    @ManyToOne
    @JoinColumn(name = "Student_id", referencedColumnName = "student_id")
    private Student student;
    @Id
    @ManyToOne
    @JoinColumn(name = "Course_id", referencedColumnName = "course_id")
    private Course course;
    private String reg_date;
    private double reg_fee;

    public Registration(String reg_no, Student student, Course course, String reg_date, double reg_fee) {
        this.reg_no = reg_no;
        this.student = student;
        this.course = course;
        this.reg_date = reg_date;
        this.reg_fee = reg_fee;
    }

    public Registration() {
    }


    public String getReg_no() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no = reg_no;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public double getReg_fee() {
        return reg_fee;
    }

    public void setReg_fee(double reg_fee) {
        this.reg_fee = reg_fee;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "reg_no='" + reg_no + '\'' +
                ", student=" + student +
                ", course=" + course +
                ", reg_date='" + reg_date + '\'' +
                ", reg_fee=" + reg_fee +
                '}';
    }
}
