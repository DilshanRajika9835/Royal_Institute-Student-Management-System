package util;/*@author:Dilshan Rajika Withanachchi*/



import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static SessionFactory sessionFactory;
    private static FactoryConfiguration factoryConfiguration;
    private Properties properties;
    private FactoryConfiguration(){
        properties=new Properties();
        try {
            properties.load(new FileInputStream("F:\\GIT Repository\\Royal_Institute Student Management System\\src\\Hibernate.properties"));
            Configuration configuration=new Configuration()
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(Course.class)
                    .addAnnotatedClass(Registration.class)
                    .addAnnotatedClass(Users.class)
                    .addAnnotatedClass(Login.class);
            sessionFactory= configuration.addProperties(properties).buildSessionFactory();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static FactoryConfiguration getInstance(){
        return factoryConfiguration==null?factoryConfiguration=new FactoryConfiguration():factoryConfiguration;
    }
    public Session getSession()
    {
        return sessionFactory.openSession();
    }


}
