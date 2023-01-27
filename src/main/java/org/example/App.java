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
            System.out.println(person);

            Hibernate.initialize(person.getItems());    // for unloading related LAZY entities

            session.getTransaction().commit();
            // after commit auto session.close();

            System.out.println("out of session");
            System.out.println(person.getItems());// can get items from hibernate cash without session
        }
    }
}
