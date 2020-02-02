package com.example.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private static final Logger log = LoggerFactory.getLogger(PersonRepositoryImpl.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void add(Person person) {
        log.info("adding " + person);
        jdbcTemplate.update("INSERT INTO person(name, dob) VALUES (?,?)",
                person.getName(),
                person.getDob());
    }

    @Override
    public List find() {
        List<Person> persons = this.jdbcTemplate.query(
                "select * from person",
                new PersonMapper());
        log.info("Found " + persons.size() + " persons");
        return persons;
    }

    @Override
    public Person getPersonByName(String name) {
        log.info("Searching for person with name => " + name );

        List<Person> person = jdbcTemplate.query("Select * from person where name = " + name, new PersonMapper());

        if(person.size() > 0){
            return person.get(0);
        }

        log.error("Person not found");
        return null;
    }

    private static class PersonMapper implements RowMapper {
        @Override
        public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setName(rs.getString("name"));
            person.setDob(rs.getDate("dob").toLocalDate());
            return person;
        }
    }
}
