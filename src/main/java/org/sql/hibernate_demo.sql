CREATE TABLE Actor
(
    id   int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar(100) NOT NULL UNIQUE,
    age  int CHECK ( age > 0 )
);
CREATE TABLE Movie
(
    id                 int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name               varchar(200) NOT NULL,
    year_of_production int check ( year_of_production > 1900 )
);
/*  Join Table:   */
CREATE TABLE Actor_Movie
(
    actor_id int REFERENCES Actor (id),
    movie_id int REFERENCES Movie (id),
    PRIMARY KEY (actor_id, movie_id)
);