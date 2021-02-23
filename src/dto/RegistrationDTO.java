package dto;/*@author:Dilshan Rajika Withanachchi*/

public class RegistrationDTO {
    private int no;
    private String reg_no;
    private String student_id;
    private String course_id;
    private String reg_date;
    private double reg_fee;

    public RegistrationDTO() {
    }

    public RegistrationDTO(int no, String reg_no, String student_id, String course_id, String reg_date, double reg_fee) {
        this.no = no;
        this.reg_no = reg_no;
        this.student_id = student_id;
        this.course_id = course_id;
        this.reg_date = reg_date;
        this.reg_fee = reg_fee;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getReg_no() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no = reg_no;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
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
        return "RegistrationDTO{" +
                "no=" + no +
                ", reg_no='" + reg_no + '\'' +
                ", student_id='" + student_id + '\'' +
                ", course_id='" + course_id + '\'' +
                ", reg_date='" + reg_date + '\'' +
                ", reg_fee=" + reg_fee +
                '}';
    }
}
