package com.hercules.model;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonTest {
    @Test
    @Order(1)
    void canCreatePersonObject() {
        Person person = new Person();
        assertNotNull(person);
    }

    @Test
    @Order(2)
    void canUpdateAndReadPerson() {
        String newPhone = "12341234";
        Person newPerson = new Person();
        newPerson.setPhone(newPhone);
        assertEquals(newPerson.getPhone(), newPhone);
    }
}