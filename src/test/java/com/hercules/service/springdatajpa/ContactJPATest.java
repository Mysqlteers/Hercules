package com.hercules.service.springdatajpa;

import com.hercules.model.Contact;
import com.hercules.service.ContactService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactJPATest {
    @Autowired
    ContactService cs;

    Long testCaseId = (long) 999999999;

    @Test
    @Order(2)
    void canFindAll() {
        assertTrue(cs.findAll().size() >= 1);
    }

    @Test
    @Order(1)
    void canSaveAndCanFindByCaseId() {
        Contact newContact = new Contact();
        newContact.setCaseId(testCaseId);
        cs.save(newContact);
        assertTrue(cs.findContactByCaseId(testCaseId).isPresent());
    }

    @Test
    @Order(4)
    void canDeleteById() {
        int sizeBefore = cs.findAll().size();
        cs.deleteById(cs.findContactByCaseId(testCaseId).get().getContactId());
        assertTrue(sizeBefore > cs.findAll().size());
    }

    @Test
    @Order(3)
    void canFindById() {
        Contact contact = cs.findContactByCaseId(testCaseId).get();
        Contact otherContact = cs.findById(contact.getContactId()).get();
        assertEquals(contact.getCaseId(), otherContact.getCaseId());
        assertEquals(contact.getContactId(), otherContact.getContactId());
    }
}