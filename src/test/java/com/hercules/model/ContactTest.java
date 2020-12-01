package com.hercules.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContactTest {
    @Test
    void canCreateContactObject() {
        Contact contact = new Contact();
        assertNotNull(contact);
    }

    @Test
    void canUpdateAndReadContact() {
        Long caseId = (long) 1;
        Contact contact = new Contact();
        contact.setCaseId(caseId);
        assertEquals(contact.getCaseId(), caseId);
    }
}