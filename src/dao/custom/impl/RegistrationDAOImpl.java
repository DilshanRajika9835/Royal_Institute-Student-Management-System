package dao.custom.impl;/*@author:Dilshan Rajika Withanachchi*/

import dao.custom.RegistrationDAO;
import entity.Registration;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public boolean add(Registration entity) throws Exception {
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
    public boolean update(Registration entity) throws Exception {
        final Session session = FactoryConfiguration.getInstance().getSession();
        final Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        return true;
    }

    @Override
    public Registration find(String s) throws Exception {
        return null;
    }

    @Override
    public List<Registration> findall() throws Exception {
        final Session session = FactoryConfiguration.getInstance().getSession();
        final Transaction transaction = session.beginTransaction();
        final Query query = session.createQuery("from Registration");
        transaction.commit();
        return query.list();
    }

    @Override
    public String genRegID(String id) throws Exception {
        final Session session = FactoryConfiguration.getInstance().getSession();
        final Transaction transaction = session.beginTransaction();
        final NativeQuery sql = session.createSQLQuery("Select reg_no from Registration  where course_id = ?1 Order " +
                "by reg_no desc limit 1;");
        final NativeQuery query = sql.setParameter(1, id);
        final String regid= (String) query.uniqueResult();
        transaction.commit();
        return regid;
    }

    @Override
    public String findRegistration(String SID, String CID) throws Exception {
        final Session session = FactoryConfiguration.getInstance().getSession();
        final Transaction transaction = session.beginTransaction();
        final NativeQuery query = session.createSQLQuery("Select Student_id from Registration where Student_id = ?1 " +
                "and " +
                " " +
                "course_id =?2");
        query.setParameter(1, SID);
        query.setParameter(2, CID);
        final String StudentID =(String) query.uniqueResult();
        transaction.commit();
        return StudentID;
    }

    @Override
    public boolean deleteRegistration(String SID, String CID, String Reg) throws Exception {
        final Session session = FactoryConfiguration.getInstance().getSession();
        final Transaction transaction = session.beginTransaction();
        final NativeQuery query = session.createSQLQuery("Delete from Registration where student_id= ?1 && course_id=" +
                " ?2 &&" +
                " reg_no= ?3");
        query.setParameter(1, SID);
        query.setParameter(2, CID);
        query.setParameter(3, Reg);
        query.executeUpdate();
        transaction.commit();
        return true;
    }


}
