package com.library.service;

import com.library.entity.Book;
import com.library.entity.Person;
import com.library.repository.PersonRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Optional<Person> find(int id) {
        return repository.findById(id);
    }

    @Transactional
    public void add(Person person) {
        repository.save(person);
    }

    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        repository.save(updatedPerson);
    }

    public List<Book> getBooksByPersonId(int id) {
        var person = repository.findById(id);
        if (person.isPresent()) {
            Hibernate.initialize(person.get().getBooks());
            return person.get().getBooks()
                    .stream()
                    .peek(book -> book.setExpired(isBookOverdue(book)))
                    .toList();
        }
        return Collections.emptyList();
    }

    private boolean isBookOverdue(Book book) {
        if(book.getBookedAt() == null) {
            return false;
        }
        var tenDaysAgo =  Instant.now().minus(Duration.ofDays(10)).getEpochSecond();
        return tenDaysAgo > book.getBookedAt().toInstant().getEpochSecond();
    }
}
