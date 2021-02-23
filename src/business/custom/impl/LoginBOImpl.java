package business.custom.impl;/*@author:Dilshan Rajika Withanachchi*/

import business.custom.LoginBO;
import dao.DAOFactory;
import dao.SuperDAO;
import dao.custom.impl.LoginDAOImpl;
import dto.LoginDTO;
import entity.Login;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;

public class LoginBOImpl implements LoginBO {
    final LoginDAOImpl loginDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.LOGIN);
    @Override
    public boolean add(LoginDTO dto) throws Exception {
        return loginDAO.add(new Login(dto.getUser_name(),dto.getPassword(),dto.getLogin_date(),dto.getLogin_time(),
                dto.getLogin_status()));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public boolean update(LoginDTO dto) throws Exception {
        return false;
    }

    @Override
    public LoginDTO find(String id) throws Exception {
        return null;
    }

    @Override
    public List<LoginDTO> findall() throws Exception {
        List<LoginDTO>loglist= new ArrayList<>();
        final List<Login> find = loginDAO.findall();
        for (Login login:find) {
       loglist.add(new LoginDTO(login.getNo(),login.getUser_name(),login.getPassword(),login.getLogin_date(),
               login.getLogin_time(),login.getLogin_status()));
        }
        return loglist;
    }

    @Override
    public String checkLogin(String user, String password) throws Exception {
       return loginDAO.checkLogin(user,password);
    }
}
