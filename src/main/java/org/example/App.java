package org.example;

import org.example.model.Principal;
import org.example.model.School;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Principal.class)
                .addAnnotatedClass(School.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Principal principal = new Principal("Jessica", 43);
            principal.setSchool(new School(212));

            session.save(principal);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}
