CREATE TABLE person (
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name varchar(255) NOT NULL UNIQUE,
    age int NOT NULL
);

CREATE TABLE book (
    id int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title varchar(255) NOT NULL,
    author varchar(255) NOT NULL,
    year int NOT NULL,
    personId int REFERENCES person(id) ON DELETE SET NULL
);

ALTER TABLE book ADD bookedAt timestamp;

INSERT INTO person(name,age) VALUES ('Ivan Ivanovich Ivanov', 23);
INSERT INTO person(name,age) VALUES ('Petr Petrovich Petrov', 31);

INSERT INTO book(title,author,year) VALUES ('Game of thrones','George Marcin', 1997);
INSERT INTO book(title,author,year) VALUES ('1984','George Orwell', 1949);