package com.library.dao;

import com.library.entity.Book;
import com.library.entity.Person;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class BookDaoImpl implements BookDao {

    private final List<Book> books;

    private final Map<Integer, Person> personsWithBook;

    public BookDaoImpl() {
        books = new LinkedList<>();
        personsWithBook = new HashMap<>();
        books.add(new Book(1,"My book 1","My author 1", 1817));
        books.add(new Book(2,"My book 2","My author 2", 1925));
    }

    @Override
    public void add(Book book) {
        book.setId(books.size() + 1);
        books.add(book);
    }

    @Override
    public void delete(int id) {
        books.stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .ifPresent(books::remove);
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public Book find(int id) {
        return books.stream()
                .filter(book -> book.getId() == id)
                .findFirst().get();
    }

    @Override
    public void update(int id, Book updatedBook) {
        books.stream()
                .filter(book -> book.getId() == id)
                .findFirst().ifPresent(
                        book -> {
                           book.setTitle(updatedBook.getTitle());
                           book.setAuthor(updatedBook.getAuthor());
                           book.setYear(updatedBook.getYear());
                        }
                );
    }

    @Override
    public void assign(int id, Person selectedPerson) {
        personsWithBook.put(id, selectedPerson);

    }

    @Override
    public void unassign(int id) {
        personsWithBook.remove(id);
    }

    @Override
    public Optional<Person> getBookOwner(int id) {
        if (personsWithBook.containsKey(id)) {
            return Optional.of(personsWithBook.get(id));
        }
        return Optional.empty();
    }
}
