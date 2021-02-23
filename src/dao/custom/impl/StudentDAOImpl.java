package dao.custom.impl;/*@author:Dilshan Rajika Withanachchi*/

import dao.custom.StudentDAO;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public boolean add(Student entity) throws Exception {
        final Session session = FactoryConfiguration.getInstance().getSession();
        final Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        return true;
    }

    @Override
    public boolean delete(String id) throws Exception {
        final Session session = FactoryConfiguration.getInstance().getSession();
        final Transaction transaction = session.beginTransaction();
        final Student student = session.load(Student.class, id);
        session.delete(student);
        transaction.commit();
        return true;
    }

    @Override
    public boolean update(Student entity) throws Exception {
        final Session session = FactoryConfiguration.getInstance().getSession();
        final Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        return true;
    }

    @Override
    public Student find(String id) throws Exception {
        final Session session = FactoryConfiguration.getInstance().getSession();
        final Transaction transaction = session.beginTransaction();
        final Query query = session.createQuery("from Student where student_id =?1");
        query.setParameter(1,id);
        final Student student= (Student) query.uniqueResult();
        transaction.commit();
        return student;
    }

    @Override
    public List<Student> findall() throws Exception {
        final Session session = FactoryConfiguration.getInstance().getSession();
        final Transaction transaction = session.beginTransaction();
        final Query query = session.createQuery("from Student");
        transaction.commit();
        return query.list();
    }

    @Override
    public String genStudentID() throws Exception {
        final Session session = FactoryConfiguration.getInstance().getSession();
        final Transaction transaction = session.beginTransaction();
        final NativeQuery sql = session.createSQLQuery("Select student_id from Student Order by student_id desc limit" +
                " 1");
        final String sid = (String)sql.uniqueResult();
        transaction.commit();
        return sid;
    }
}
