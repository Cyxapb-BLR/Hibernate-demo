package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;

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

            Person person = new Person("Test cascading", 30);

            Item item = new Item("Test cascading item", person);    // item with person in hibernate cash
            person.setItems(new ArrayList<Item>(Collections.singletonList(item)));  // person with item in hibernate cash

            session.persist(person);        //save person and item in DB with cascading
            //session.persist(item);      //hibernate does it automatically with  session.persist(person)

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }
}
