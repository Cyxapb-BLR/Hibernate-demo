package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass()
                .addAnnotatedClass();

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) {  //try with resources
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();


            session.getTransaction().commit();
        }
    }
}
