package dao.custom;/*@author:Dilshan Rajika Withanachchi*/

import dao.SuperDAO;
import entity.Users;

import java.util.List;

public interface UserDAO extends SuperDAO<Users,String> {
    public List<Users> findUser(String username, String password)throws Exception;
    public boolean delete(String username,String password) throws Exception;
}
