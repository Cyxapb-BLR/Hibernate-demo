package org.example;

import org.example.model.Director;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Director.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Director director = session.get(Director.class, 2); //id=2
            Movie movie = new Movie("New Movie", 2000, director);

            director.getMovies().add(movie);    //for hibernate cash

            session.save(movie);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }
}
