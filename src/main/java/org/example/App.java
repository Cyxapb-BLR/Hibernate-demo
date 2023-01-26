package org.example;

import org.example.model.Director;
import org.example.model.Movie;
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
        Configuration configuration = new Configuration().addAnnotatedClass(Director.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Director director = new Director("New Director", 50);
            Movie movie = new Movie("New Movie2", 1998, director);

            director.setMovies(new ArrayList<Movie>(Collections.singletonList(movie)));     //for hibernate cash

            session.save(director);
            session.save(movie);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }
}
