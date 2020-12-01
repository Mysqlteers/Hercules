package com.hercules.service.springdatajpa;

import com.hercules.model.Person;
import com.hercules.repository.PersonRepository;
import com.hercules.service.PersonService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
        Set<Person> personSet = new HashSet<>();
        pr.findAll().forEach(personSet::add);
        return personSet;
    }

    @Override
    public Person save(Person object) {
        return pr.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        pr.deleteById(aLong);
    }

    @Override
    public Optional<Person> findById(Long aLong) {
        return pr.findById(aLong);
    }

    @Override
    public Optional<Person> findPersonByPhone(String phone) {
        return pr.findPersonByPhone(phone);
    }
}
