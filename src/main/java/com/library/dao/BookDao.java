package com.library.dao;

import com.library.entity.Book;
import com.library.entity.Person;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    void add(Book book);

    void delete(int id);

    List<Book> findAll();

    Book find(int id);

    void update(int id, Book updatedBook);

    void assign(int id, Person selectedPerson);

    void unassign(int id);

    Optional<Person> getBookOwner(int id);
}
