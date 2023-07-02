package com.library.dao;

import com.library.entity.Book;
import com.library.entity.Person;

import java.util.List;

public interface PersonDao {

    void add(Person person);

    void delete(int id);

    List<Person> findAll();

    Person find(int id);

    void update(int id, Person updatedPerson);

    List<Book> getBooksByPersonId(int id);

}
