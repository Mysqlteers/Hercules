package com.hercules.service;

import com.hercules.model.Person;

import java.util.Optional;

public interface PersonService extends CrudService<Person, Long> {
    Optional<Person> findPersonByPhone(String phone);
}
