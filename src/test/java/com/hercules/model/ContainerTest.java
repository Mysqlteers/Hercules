package com.hercules.model;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContainerTest {

    @Test
    @Order(1)
    void canCreateContainerObject() {
        assertNotNull(new Container());
    }

    @Test
    @Order(2)
    void canUpdateAndReadContainerObject() {
        Container container = new Container();
        container.setDailyCost(500);
        assertEquals(500, container.getDailyCost());
    }

    @Test
    @Order(3)
    void canCalculateTotal() {
        Container container = new Container();
        container.setPickedUp(false);
        container.setNumberOfDays(40);
        container.setDailyCost(300.5);
        container.setPickUpPrice(999.95);

        assertEquals(12020.0, container.calculateTotal());

        container.setPickedUp(true);
        assertEquals(13019.95, container.calculateTotal());
    }

}