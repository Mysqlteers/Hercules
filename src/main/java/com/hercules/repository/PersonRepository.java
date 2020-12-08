package com.hercules.repository;

import com.hercules.model.Contact;
import com.hercules.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface PersonRepository extends CrudRepository<Person, Long> {
    Optional<Person> findPersonByPhone(String phone);
    List<Person> findAllByContactOrderByPositionAscFirstNameAsc(Contact contact);
}
