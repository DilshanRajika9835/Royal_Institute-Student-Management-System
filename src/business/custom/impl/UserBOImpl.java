package business.custom.impl;/*@author:Dilshan Rajika Withanachchi*/

import business.custom.UserBO;
import dao.DAOFactory;
import dao.custom.impl.UserDAOImpl;
import dto.UserDTO;
import entity.Users;

import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    final UserDAOImpl userDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);
    @Override
    public boolean addUser(UserDTO dto) throws Exception {
       return userDAO.add(new Users(dto.getUsername(),dto.getPassword(),dto.getProfile()));
    }

    @Override
    public boolean delete(String username, String password) throws Exception {
        return userDAO.delete(username,password);
    }

    @Override
    public List<UserDTO> findall() throws Exception {
        List<UserDTO>userlist=new ArrayList<>();
        final List<Users> findall = userDAO.findall();
        int no=1;
        for (Users user:findall) {
         userlist.add(new UserDTO(no++,user.getUsername(),user.getPassword(),user.getProfile()));
        }
        return userlist;
    }

    @Override
    public List<UserDTO> findUser(String username, String password) throws Exception {
        List<UserDTO>userlist=new ArrayList<>();
        final List<Users> findall = userDAO.findUser(username,password);
        for (Users user:findall) {
            userlist.add(new UserDTO(user.getId(),user.getUsername(),user.getPassword(),user.getProfile()));
        }
        return userlist;
    }

}
