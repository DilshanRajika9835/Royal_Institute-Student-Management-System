package dao.custom;/*@author:Dilshan Rajika Withanachchi*/

import dao.SuperDAO;
import entity.Login;

public interface LoginDAO extends SuperDAO<Login,String> {
    public String checkLogin(String user,String password)throws Exception;
}
