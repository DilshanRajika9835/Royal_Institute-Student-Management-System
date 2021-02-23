package business.custom;/*@author:Dilshan Rajika Withanachchi*/

import business.SuperBO;
import dto.StudentDTO;

import java.util.List;

public interface StudentBO extends SuperBO {
    public  boolean add(StudentDTO dto)throws Exception;
    public  boolean delete(String id)throws Exception;
    public  boolean update(StudentDTO dto)throws Exception;
    public  StudentDTO find(String id)throws Exception;
    public List<StudentDTO> findall()throws Exception;
    public String genstudentID()throws Exception;
}
