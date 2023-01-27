package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Person person = session.get(Person.class, 1);
            System.out.println("got person from table");

            session.getTransaction().commit();
            // after commit auto session.close();

            System.out.println("session ended (session.close())");

            //open session and transaction
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println("inside 2nd transaction");

            person = (Person) session.merge(person);

            Hibernate.initialize(person.getItems());

            session.getTransaction().commit();

            System.out.println("outside of 2nd");

            //it works, because related items was unloaded
            System.out.println(person.getItems());

        }
    }
}
