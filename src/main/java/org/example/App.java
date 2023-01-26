package org.example;

import org.example.model.Director;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

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

            Director director = session.get(Director.class, 2);     //id=2
            System.out.println(director);

            List<Movie> movies = director.getMovies();
            for (Movie movie : movies) {
                System.out.println(movie.getName());
            }

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }
}
