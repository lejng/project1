package com.library.service;

import com.library.entity.Book;
import com.library.entity.Person;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> findByTitleStartingWith(String titlePart) {
        return repository.findByTitleStartingWith(titlePart);
    }

    public List<Book> findAll(int page, int booksPerPage, boolean isSortByYear) {
        PageRequest pageable = PageRequest.of(page - 1, booksPerPage);
        if (isSortByYear) {
            pageable.withSort(Sort.by("year"));
        }
        return repository.findAll(pageable).stream().toList();
    }

    public List<Book> findAll(boolean isSortByYear) {
        if (isSortByYear) {
            return repository.findAll(Sort.by("year"));
        }
        return repository.findAll();
    }

    public Optional<Book> find(int id) {
        return repository.findById(id);
    }

    @Transactional
    public void add(Book book) {
        repository.save(book);
    }

    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        repository.save(updatedBook);
    }

    @Transactional
    public void assign(int bookId, Person owner) {
        repository.findById(bookId).ifPresent(book -> {
            book.setBookedAt(Timestamp.from(Instant.now()));
            book.setOwner(owner);
            repository.save(book);
        });
    }

    @Transactional
    public void unassign(int bookId) {
        repository.findById(bookId).ifPresent(book -> {
            book.setBookedAt(null);
            book.setOwner(null);
            repository.save(book);
        });
    }

}
