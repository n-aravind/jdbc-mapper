package com.example.mapper;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonRepositoryImplTest {
    Random random = new Random();

    @Autowired
    PersonRepository personRepository;

    @Test
    public void testCreate() {
        Person person = this.createRandomPerson();
        personRepository.add(person);
        List<Person> persons = personRepository.find();
        assertNotNull(persons);
        assertTrue(persons.size() > 0);
        boolean found = false;
        for (Person p : persons) {
            if (p.equals(person)) {
                found = true;
                break;
            }
        }

        assertTrue(found, "Could not find " + person);
    }

    private Person createRandomPerson() {
        Person person = new Person();
        person.setName(Integer.toString(random.nextInt()));
        person.setDob(LocalDate.now());
        return person;
    }


    @Test
    public void findPersonByNameTest(){
        Person person = this.createRandomPerson();
        personRepository.add(person);
        Person returnedPerson = personRepository.getPersonByName(person.getName());
        assertTrue(returnedPerson.equals(person));
    }
}