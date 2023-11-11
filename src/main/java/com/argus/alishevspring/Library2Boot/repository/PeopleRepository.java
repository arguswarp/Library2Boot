package com.argus.alishevspring.Library2Boot.repository;


import com.argus.alishevspring.Library2Boot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByFullName(String fullname);

}
