package com.hercules.service.springdatajpa;

import com.hercules.model.Contact;
import com.hercules.model.Person;
import com.hercules.service.ContactService;
import com.hercules.service.PersonService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonJPATest {
    @Autowired
    PersonService ps;

    @Autowired
    ContactService cs;

    private String TESTPHONE = "12341234";
    private Long TESTCASEID = (long) 999999999;


    @Test
    @Order(2)
    void canFindAll() {
        assertTrue(ps.findAll().size() >= 1);
    }

    @Test
    @Order(1)
    void canSaveAndFindByPhone() {
        Contact testContact = new Contact();
        testContact.setCaseId(TESTCASEID);
        cs.save(testContact);
        Person person = new Person();
        person.setFirstName("Tom");
        person.setLastName("Tomsen");
        person.setPhone(TESTPHONE);
        person.setEmail("testmail@testme.com");
        person.setContact(cs.findContactByCaseId(TESTCASEID).get());

        ps.save(person);
        assertTrue(ps.findPersonByPhone(TESTPHONE).isPresent());
    }

    @Test
    @Order(5)
    void canDeleteById() {
        int sizeBefore = ps.findAll().size();
        ps.deleteById(ps.findPersonByPhone(TESTPHONE).get().getPersonId());
        cs.deleteById(cs.findContactByCaseId(TESTCASEID).get().getContactId());
        assertTrue(sizeBefore > ps.findAll().size());
    }

    @Test
    @Order(3)
    void canFindById() {
        Person person = ps.findPersonByPhone(TESTPHONE).get();
        Person otherPerson = ps.findById(person.getPersonId()).get();
        assertEquals(person.getPhone(), otherPerson.getPhone());
        assertEquals(person.getPersonId(), otherPerson.getPersonId());
    }

    @Test
    @Order(4)
    void canUpdatePerson() {
        String newMail = "newandimproved@mail.dk";
        Person person = ps.findPersonByPhone(TESTPHONE).get();
        person.setEmail(newMail);
        ps.save(person);
        Person newPerson = ps.findById(person.getPersonId()).get();
        assertEquals(person.getFirstName(), newPerson.getFirstName());
        assertEquals(newMail, newPerson.getEmail());
    }
}