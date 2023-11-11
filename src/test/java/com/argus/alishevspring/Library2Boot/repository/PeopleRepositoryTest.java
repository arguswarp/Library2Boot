package com.argus.alishevspring.Library2Boot.repository;

import com.argus.alishevspring.Library2Boot.model.Book;
import com.argus.alishevspring.Library2Boot.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PeopleRepositoryTest {

    @Autowired
    private PeopleRepository peopleRepository;

    @Test
    void PeopleRepository_SaveAllAndSetBook_ReturnSavedPerson() {
        //Arrange

        Person person = Person.builder()
                .fullName("Ivan Ivanovich")
                .ageOfBirth(1975)
                .build();
        Book book = Book.builder()
                .title("Dune")
                .author("Frank Herbert")
                .ageOfPublishment(1965)
                .build();
        person.setBooks(Collections.singletonList(book));
        book.setOwner(person);

        //Act
        Person savedPerson = peopleRepository.save(person);

        //Assert
        Assertions.assertNotNull(savedPerson);
        Assertions.assertNotNull(savedPerson.getBooks());
        Assertions.assertNotEquals(savedPerson.getPersonId(), 0);
    }
}
