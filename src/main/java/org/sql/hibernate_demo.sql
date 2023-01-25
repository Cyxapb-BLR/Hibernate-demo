CREATE TABLE Person
(
    id   int PRIMARY KEY,
    name varchar(100),
    age  int
);
CREATE SEQUENCE person_id_seq;

INSERT INTO Person(id, name, age)
VALUES (1, 'TestPerson', 50);

SELECT *
FROM Person;

DROP TABLE Person;