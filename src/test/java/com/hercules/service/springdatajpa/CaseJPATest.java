package com.hercules.service.springdatajpa;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CaseJPATest {

    @Test
    @Order(2)
    void canFindAll() {
    }

    @Order(1)
    @Test
    void canSave() {
    }

    @Test
    @Order(4)
    void canDeleteById() {
    }

    @Test
    @Order(3)
    void canFindById() {
    }
}