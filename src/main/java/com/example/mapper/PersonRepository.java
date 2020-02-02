package com.example.mapper;

import java.util.List;

public interface PersonRepository {

    void add(Person person);
    List<Person> find();
    Person getPersonByName(String name);
}
