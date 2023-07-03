package com.library.dao;

import com.library.entity.Book;
import com.library.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BookDaoImpl implements BookDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(Book book) {
        jdbcTemplate.update(
                "INSERT INTO book(title, author, year) VALUES(?, ?, ?)",
                book.getTitle(),
                book.getAuthor(),
                book.getYear()
        );
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public Book find(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM book WHERE id=?",
                new BeanPropertyRowMapper<>(Book.class),
                id)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(int id, Book updatedBook) {
        jdbcTemplate.update(
                "UPDATE book SET title=?, author=?, year=? WHERE id=?",
                updatedBook.getTitle(),
                updatedBook.getAuthor(),
                updatedBook.getYear(),
                id);
    }

    @Override
    public void assign(int id, Person selectedPerson) {
        jdbcTemplate.update(
                "UPDATE book SET personId=? WHERE id=?",
                selectedPerson.getId(),
                id);
    }

    @Override
    public void unassign(int id) {
        jdbcTemplate.update("UPDATE book SET personId=NULL WHERE id=?", id);
    }

    @Override
    public Optional<Person> getBookOwner(int bookId) {
        return jdbcTemplate.query(
                "SELECT * FROM person WHERE id IN (SELECT personId FROM book WHERE id=?)",
                new BeanPropertyRowMapper<>(Person.class),
                        bookId)
                .stream()
                .findFirst();
    }
}
