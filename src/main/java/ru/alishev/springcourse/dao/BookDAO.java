package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Select all books
    public List<Book> index() {
        return jdbcTemplate.query("SELECT id_book, title, author, year_of_writing FROM Book",
                new BeanPropertyRowMapper<>(Book.class));
    }

    //Update book, free
    public void freeBook(int id){
        jdbcTemplate.update("UPDATE book SET id_person=null WHERE id_book=?", id);
    }

    //Book id search 
    public Book showIdBook(int id) {
        return (Book) jdbcTemplate.query("SELECT * FROM book WHERE id_book=? ", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    //Appoint book, update
    public void bookAppoint(int idBook, int idPerson){
        jdbcTemplate.update("UPDATE book SET id_person=? WHERE id_book=?", idPerson, idBook);
    }

    //Insert book
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(title, author, year_of_writing) VALUES(?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYearOfWriting());
    }

    //Edit book
    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE book SET title=?, author=?, year_of_writing=? WHERE id_book=?",
                book.getTitle(), book.getAuthor(), book.getYearOfWriting(), id);
    }

    //Select get book Owner
    public Optional<Person> getBookOwner(int id){
        return jdbcTemplate.query("SELECT person.* FROM book JOIN person ON book.id_person=person.id_person " +
                "WHERE id_book=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    //Delete book
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id_book=?", id);
    }
}
