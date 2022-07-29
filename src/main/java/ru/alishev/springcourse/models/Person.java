package ru.alishev.springcourse.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class Person {
    private int idPerson;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 70, message = "Name should be between 2 and 70 characters")
    private String fio;

    @Min(value = 0, message = "Age should be greater than 0")
    private int yearOfBirth;

    public Person() {}

    public Person(int id, String fio, int yearOfBirth) {
        this.idPerson = id;
        this.fio = fio;
        this.yearOfBirth = yearOfBirth;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
