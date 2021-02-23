package view.tm;/*@author:Dilshan Rajika Withanachchi*/

public class RegistrationTM {
    private int no;
    private String reg;
    private String sid;
    private String sname;
    private String cid;
    private String cname;
    private String regdate;

    public RegistrationTM(int no, String reg, String sid, String sname, String cid, String cname, String regdate) {
        this.no = no;
        this.reg = reg;
        this.sid = sid;
        this.sname = sname;
        this.cid = cid;
        this.cname = cname;
        this.regdate = regdate;
    }

    public RegistrationTM() {
    }


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    @Override
    public String toString() {
        return "RegistrationTM{" +
                "no=" + no +
                ", reg='" + reg + '\'' +
                ", sid='" + sid + '\'' +
                ", sname='" + sname + '\'' +
                ", cid='" + cid + '\'' +
                ", cname='" + cname + '\'' +
                ", regdate='" + regdate + '\'' +
                '}';
    }
}
