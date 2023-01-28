package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

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

            List<Item> items = session.createQuery("select i from Item i where i.owner.id=:personId", Item.class)
                    .setParameter("personId", person.getId()).getResultList();

            System.out.println(items);

            session.getTransaction().commit();

            System.out.println("outside of 2nd");
        }
    }
}
