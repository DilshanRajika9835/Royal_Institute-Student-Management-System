package dao.custom.impl;/*@author:Dilshan Rajika Withanachchi*/

import dao.custom.LoginDAO;
import entity.Login;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;

public class LoginDAOImpl implements LoginDAO {
    @Override
    public boolean add(Login entity) throws Exception {
        final Session session = FactoryConfiguration.getInstance().getSession();
        final Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        return false;
    }

    @Override
    public boolean update(Login entity) throws Exception {
        return false;
    }

    @Override
    public Login find(String s) throws Exception {
        return null;
    }

    @Override
    public List<Login> findall() throws Exception {
        final Session session = FactoryConfiguration.getInstance().getSession();
        final Transaction transaction = session.beginTransaction();
        final Query query = session.createQuery("from Login");
        transaction.commit();
        return query.list();
    }

    @Override
    public String checkLogin(String user, String password) throws Exception {
        final Session session = FactoryConfiguration.getInstance().getSession();
        final Transaction transaction = session.beginTransaction();
        final Query query = session.createSQLQuery("Select password from Users where username = ?1 &&  password = ?2");
        query.setParameter(1,user);
        query.setParameter(2,password);
        final String code = (String)query.uniqueResult();
        transaction.commit();
        return code;
    }
}
