package com.hercules.service.springdatajpa;

import com.hercules.model.Person;
import com.hercules.repository.PersonRepository;
import com.hercules.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PersonJPA implements PersonService {
    private PersonRepository pr;

    public PersonJPA(PersonRepository pr) {
        this.pr = pr;
    }

    @Override
    public Set<Person> findAll() {
        return null;
    }

    @Override
    public Person save(Person object) {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Optional<Person> findById(Long aLong) {
        return Optional.empty();
    }
}
