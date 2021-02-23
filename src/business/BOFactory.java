package business;/*@author:Dilshan Rajika Withanachchi*/

import business.custom.impl.*;

public class BOFactory {
    private static  BOFactory boFactory;
    private BOFactory (){


    }
public  static BOFactory getInstance(){
    return boFactory==null?boFactory=new BOFactory():boFactory;
    }
public  <T extends SuperBO >T getBo(BoType getbo){
    switch (getbo){
        case COURSE:return (T) new CourseBOImpl();
        case STUDENT:return (T) new StudentBOImpl();
        case REGISTRATION:return (T) new RegistrationBoImpl();
        case LOGIN:return (T) new LoginBOImpl();
        case USER:return (T) new UserBOImpl();
        default:return null;
    }
}
    public enum BoType{
        STUDENT,REGISTRATION,COURSE,LOGIN,USER
    }
}

