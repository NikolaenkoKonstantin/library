package ru.alishev.springcourse.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class Book {
    private int idBook;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 70, message = "Name should be between 2 and 70 characters")
    private String title;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 70, message = "Name should be between 2 and 70 characters")
    private String author;

    @Min(value = 0, message = "Age should be greater than 0")
    private int yearOfWriting;

    public Book(){}

    public Book(int idBook, int idPerson, String title, String author, int yearOfWriting) {
        this.idBook = idBook;
        this.title = title;
        this.author = author;
        this.yearOfWriting = yearOfWriting;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
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

    public int getYearOfWriting() {
        return yearOfWriting;
    }

    public void setYearOfWriting(int yearOfWriting) {
        this.yearOfWriting = yearOfWriting;
    }
}
