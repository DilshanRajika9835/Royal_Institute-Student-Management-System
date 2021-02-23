package business.custom;/*@author:Dilshan Rajika Withanachchi*/

import business.SuperBO;
import dto.LoginDTO;
import dto.RegistrationDTO;
import dto.StudentDTO;

import java.util.List;

public interface LoginBO extends SuperBO {
    public  boolean add(LoginDTO dto)throws Exception;
    public  boolean delete(String id)throws Exception;
    public  boolean update(LoginDTO dto)throws Exception;
    public LoginDTO find(String id)throws Exception;
    public List<LoginDTO> findall()throws Exception;
    public String checkLogin(String user,String password)throws Exception;
}
