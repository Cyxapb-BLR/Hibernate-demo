package org.example;

import org.example.model.Item;
import org.example.model.Person;
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
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();

            Person person = session.get(Person.class, 2);
            session.remove(person);     //remove person from db

            /*List<Item> items = person.getItems();
            for (Item item : items) {
                item.setOwner(null);
            }*/
            person.getItems().forEach(item -> item.setOwner(null));     //remove person for hibernate cash

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }
}
