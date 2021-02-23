package dto;/*@author:Dilshan Rajika Withanachchi*/

public class CourseDTO {
    private int no;
    private String course_id;
    private String course_name;
    private String duration;
    private double course_fee;

    public CourseDTO() {
    }

    public CourseDTO(int no, String course_id, String course_name, String duration, double course_fee) {
        this.no = no;
        this.course_id = course_id;
        this.course_name = course_name;
        this.duration = duration;
        this.course_fee = course_fee;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
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

    @Override
    public String toString() {
        return "CourseDTO{" +
                "no=" + no +
                ", course_id='" + course_id + '\'' +
                ", course_name='" + course_name + '\'' +
                ", duration='" + duration + '\'' +
                ", course_fee=" + course_fee +
                '}';
    }
}
