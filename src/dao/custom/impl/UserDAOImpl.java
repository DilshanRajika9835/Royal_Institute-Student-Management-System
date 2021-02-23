package dao.custom.impl;/*@author:Dilshan Rajika Withanachchi*/

import dao.custom.UserDAO;
import entity.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean add(Users entity) throws Exception {
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
    public boolean update(Users entity) throws Exception {
        return false;
    }

    @Override
    public Users find(String s) throws Exception {
        return null;
    }

    @Override
    public List<Users> findall() throws Exception {
        final Session session = FactoryConfiguration.getInstance().getSession();
        final Transaction transaction = session.beginTransaction();
        final Query query = session.createQuery("from Users");
        transaction.commit();
        return query.list();
    }

    @Override
    public List<Users> findUser(String name, String pwd) throws Exception {
        final Session session = FactoryConfiguration.getInstance().getSession();
        final Transaction transaction = session.beginTransaction();
        final Query query = session.createQuery("from Users where username = ?1 and password = ?2");
        query.setParameter(1, name);
        query.setParameter(2, pwd);
        transaction.commit();
        return query.list();
    }

    @Override
    public boolean delete(String Uname, String Pwd) throws Exception {
        final Session session = FactoryConfiguration.getInstance().getSession();
        final Transaction transaction = session.beginTransaction();
        final NativeQuery query = session.createSQLQuery("Delete from Users where username = ?1 && password = ?2");
        query.setParameter(1, Uname);
        query.setParameter(2, Pwd);
        query.executeUpdate();
        transaction.commit();
        return true;
    }
}
