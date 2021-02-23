package dao;/*@author:Dilshan Rajika Withanachchi*/

import business.custom.impl.LoginBOImpl;
import dao.custom.impl.*;

public class DAOFactory {
    private static  DAOFactory daoFactory;
    private DAOFactory(){

    }
    public  static DAOFactory getInstance(){
        return daoFactory==null?daoFactory=new DAOFactory():daoFactory;
    }
    public <T extends SuperDAO>T getDAO(DAOType getdao){
        switch (getdao){
            case COURSE:return(T) new CourseDAOImpl();
            case REGISTRATION:return (T)new RegistrationDAOImpl();
            case STUDENT:return(T)new StudentDAOImpl();
            case QUERY:return(T)new QueryDAOImpl();
            case LOGIN:return(T)new LoginDAOImpl();
            case USER:return(T)new UserDAOImpl();
            default:return null;

        }
    }
    public enum DAOType{
        STUDENT,REGISTRATION,COURSE,QUERY,LOGIN,USER
    }
}

