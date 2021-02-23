package dao.custom;/*@author:Dilshan Rajika Withanachchi*/

import dao.SuperDAO;
import entity.Student;

public interface StudentDAO extends SuperDAO<Student,String> {
    public String genStudentID()throws Exception;
}
