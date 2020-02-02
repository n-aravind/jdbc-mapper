drop table IF EXISTS person;

create TABLE person(
 id INT AUTO_INCREMENT  PRIMARY KEY,
 name VARCHAR(250) NOT NULL,
 dob DATE NOT NULL
 );