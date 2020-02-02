package com.example.mapper;

import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
public class Person {
    private int id;
    private String name;
    private LocalDate dob;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(dob, person.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dob);
    }
}
