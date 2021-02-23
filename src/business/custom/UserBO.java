package business.custom;/*@author:Dilshan Rajika Withanachchi*/

import business.SuperBO;
import dto.LoginDTO;
import dto.UserDTO;

import java.util.List;

public interface UserBO extends SuperBO {
    public boolean addUser(UserDTO dto) throws Exception;
    public boolean delete(String username,String password) throws Exception;
    public List<UserDTO> findall()throws Exception;
    public List<UserDTO>  findUser(String username,String password)throws Exception;
}
