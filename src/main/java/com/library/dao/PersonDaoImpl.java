package com.library.dao;

import com.library.entity.Book;
import com.library.entity.Person;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class PersonDaoImpl implements PersonDao {

    private final List<Person> persons;

    public PersonDaoImpl() {
        persons = new LinkedList<>();
        persons.add(new Person(1, "Ivan Ivanov Ivanovich", 23));
        persons.add(new Person(2, "Petr Petrov Petrovich", 25));
    }

    @Override
    public void add(Person person) {
        person.setId(persons.size() + 1);
        persons.add(person);
    }

    @Override
    public void delete(int id) {
        persons.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .ifPresent(persons::remove);
    }

    @Override
    public List<Person> findAll() {
        return persons;
    }

    @Override
    public Person find(int id) {
        return persons.stream()
                .filter(person -> person.getId() == id)
                .findFirst().get();
    }

    @Override
    public void update(int id, Person updatedPerson) {
        persons.stream()
                .filter(person -> person.getId() == id)
                .findFirst().ifPresent(
                        person -> {
                            person.setAge(updatedPerson.getAge());
                            person.setName(updatedPerson.getName());
                        }
                );
    }

    @Override
    public List<Book> getBooksByPersonId(int id) {
        return List.of();
    }

}
