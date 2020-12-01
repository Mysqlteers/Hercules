package com.hercules.model;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CaseTest {

    @Test
    @Order(1)
    public void canCreateCaseObject(){
        Case newCase = new Case();
        assertNotNull(newCase);
    }

    @Test
    @Order(2)
    public void canUpdateAndReadCase(){
        String newDesc = "New description";
        Case newCase = new Case();
        newCase.setDescription(newDesc);
        assertEquals(newCase.getDescription(), newDesc);
    }
}