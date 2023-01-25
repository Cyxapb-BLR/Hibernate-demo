package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Person person = new Person("Vadim", 34);
            Item newItem = new Item("DellMonitor", person); // person for item

            person.setItems(new ArrayList<Item>(Collections.singletonList(newItem)));   // item for person

            session.save(person);
            session.save(newItem);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }
}
