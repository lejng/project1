package com.library.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {

    private int id;

    @NotEmpty(message = "Book name cannot be null")
    @Size(min = 2, max = 100, message = "Book name is too short")
    private String title;

    @NotEmpty(message = "Author name can not be null")
    @Size(min = 2, max = 100, message = "Author name is too short")
    private String author;

    @Min(value = 1500, message = "Year must be more than 1500")
    private int year;

    public Book() {

    }

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
