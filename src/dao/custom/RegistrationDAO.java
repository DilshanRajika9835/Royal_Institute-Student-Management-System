package dao.custom;/*@author:Dilshan Rajika Withanachchi*/

import dao.SuperDAO;
import entity.Registration;
import view.tm.RegistrationTM;

import java.util.List;


public interface RegistrationDAO extends SuperDAO<Registration,String> {
    public String genRegID(String id)throws Exception;
    public String findRegistration(String SID,String CID)throws Exception;
    public boolean deleteRegistration(String SID,String CID,String Reg)throws Exception;

}
