package dao.custom.impl;/*@author:Dilshan Rajika Withanachchi*/

import dao.custom.QueryDAO;
import entity.SuperEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.Serializable;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public  List<Object[]> getRegStudents(String CID) throws Exception {
        final Session session = FactoryConfiguration.getInstance().getSession();
        final Transaction transaction = session.beginTransaction();
        final Query query = session.createSQLQuery("Select R.reg_no,S.Student_id,S.Student_name,C.course_id,C" +
                ".course_name,R.reg_date from Student S,Course C,Registration R where S.Student_id = R.Student_id && " +
                "C.course_id = R.course_id && C.course_id = ?1 order by R.reg_no asc");
        query.setParameter(1,CID);
        List<Object[]> list =query.list();
        transaction.commit();
      return list;
    }


    @Override
    public boolean add(SuperEntity entity) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Serializable serializable) throws Exception {
        return false;
    }

    @Override
    public boolean update(SuperEntity entity) throws Exception {
        return false;
    }

    @Override
    public SuperEntity find(Serializable serializable) throws Exception {
        return null;
    }

    @Override
    public List findall() throws Exception {
        return null;
    }
}
