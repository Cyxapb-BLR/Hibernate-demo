package org.example;

import org.example.model.Actor;
import org.example.model.Movie;
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
        Configuration configuration = new Configuration().addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) {  //try with resources
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            Movie movie = new Movie("Pulp fiction", 1994);
            Actor actor1 = new Actor("Harvei Keytel", 81);
            Actor actor2 = new Actor("Samuel L.Jackson", 72);

            //  Arrays.asList(actor1,actor2)
            movie.setActors(new ArrayList<>(List.of(actor1, actor2)));

            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie)));    //for hibernate cash
            actor2.setMovies(new ArrayList<>(Collections.singletonList(movie)));    //for hibernate cash

            session.save(movie);

            session.save(actor1);
            session.save(actor2);

            session.getTransaction().commit();

        }
    }
}
