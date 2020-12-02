package com.hercules.repository;

import com.hercules.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface PersonRepository extends CrudRepository<Person, Long> {
    Optional<Person> findPersonByPhone(String phone);
}
