package com.library.dao;

import com.library.entity.Book;
import com.library.entity.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDaoImpl implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    public PersonDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(Person person) {
        jdbcTemplate.update(
                "INSERT INTO person(name, age) VALUES(?, ?)",
                person.getName(),
                person.getAge()
        );
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }

    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    @Override
    public Person find(int id) {
        return jdbcTemplate.query(
                        "SELECT * FROM person WHERE id=?",
                        new BeanPropertyRowMapper<>(Person.class),
                        id)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update(
                "UPDATE person SET name=?, age=? WHERE id=?",
                updatedPerson.getName(),
                updatedPerson.getAge(),
                id);
    }

    @Override
    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM book WHERE personId=?",
                new BeanPropertyRowMapper<>(Book.class),
                id);
    }

}
