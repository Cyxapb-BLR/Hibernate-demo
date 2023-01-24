CREATE TABLE Person
(
    id   int PRIMARY KEY,
    name varchar(100),
    age  int
);
INSERT INTO Person(id, name, age) VALUES (1,'TestPerson',50);

SELECT *FROM Person;