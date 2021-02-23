package dao.custom;/*@author:Dilshan Rajika Withanachchi*/



import dao.SuperDAO;
import view.tm.RegistrationTM;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    public List<Object[]> getRegStudents(String CID)throws Exception;
}
