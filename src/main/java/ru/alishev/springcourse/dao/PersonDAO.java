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
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Output people
    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    //Select get books by person id
    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id_person=? ", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }

    //Select person by id
    public Optional<Person> show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id_person=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    //Insert person
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(fio, year_of_birth) VALUES(?, ?)",
                person.getFio(), person.getYearOfBirth());
    }

    //Edit person
    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET fio=?, year_of_birth=? WHERE id_person=?",
                updatedPerson.getFio(), updatedPerson.getYearOfBirth(), id);
    }

    //To validate the uniqueness of the full name
    public Optional<Person> getPersonByFullName(String fullName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE fio=?", new Object[]{fullName},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    //Delete person
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id_person=?", id);
    }
}
