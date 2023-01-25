package org.example;

import org.example.model.Item;
import org.example.model.Person;
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
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Person person = session.get(Person.class, 2);   //id=2

            Item newItem = new Item("PixelPhone", person);  // person for item in DB

            person.getItems().add(newItem);     //item for person  in hibernate cash

            session.save(newItem);  //save item in DB with person relationship

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }
}
