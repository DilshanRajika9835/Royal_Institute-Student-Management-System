package dao.custom.impl;/*@author:Dilshan Rajika Withanachchi*/

import dao.custom.CourseDAO;
import entity.Course;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public boolean add(Course entity) throws Exception {
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
        final Course course = session.load(Course.class, id);
        session.delete(course);
        transaction.commit();
        return true;
    }

    @Override
    public boolean update(Course entity) throws Exception {
        final Session session = FactoryConfiguration.getInstance().getSession();
        final Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        return true;
    }

    @Override
    public Course find(String id) throws Exception {
        final Session session = FactoryConfiguration.getInstance().getSession();
        final Transaction transaction = session.beginTransaction();
        final Query query = session.createQuery("from Course where course_id =?1");
        query.setParameter(1,id);
        final Course course= (Course) query.uniqueResult();
        transaction.commit();
        return course;
    }

    @Override
    public List<Course> findall() throws Exception {
        final Session session = FactoryConfiguration.getInstance().getSession();
        final Transaction transaction = session.beginTransaction();
        final Query query = session.createQuery("from Course");
        transaction.commit();
        return query.list();
    }
}
