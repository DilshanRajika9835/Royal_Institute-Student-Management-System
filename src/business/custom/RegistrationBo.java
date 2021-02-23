package business.custom;/*@author:Dilshan Rajika Withanachchi*/

import business.SuperBO;
import dto.RegistrationDTO;
import dto.StudentDTO;
import javafx.collections.ObservableList;
import view.tm.RegistrationTM;

import java.util.List;

public interface RegistrationBo extends SuperBO {
    public  boolean add(RegistrationDTO dto)throws Exception;
    public  boolean delete(String id)throws Exception;
    public  boolean update(RegistrationDTO dto)throws Exception;
    public  StudentDTO find(String id)throws Exception;
    public List<RegistrationDTO> findall()throws Exception;
    public String genRegID(String id)throws Exception;
    public String findRegistration(String SID,String CID)throws Exception;
    public boolean deleteRegistration(String SID,String CID,String Reg)throws Exception;
    public ObservableList<RegistrationTM> getRegStudents(String CID)throws Exception;
}
