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

            Movie movie = session.get(Movie.class, 14);     //id=14
            Director director = session.get(Director.class, 7);

            movie.getDirector().getMovies().remove(movie);  //remove for old director for hibernate cash

            movie.setDirector(director);    //new director in db

            director.getMovies().add(movie);    // new movie for director for hibernate cash

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }
}
