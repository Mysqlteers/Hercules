package com.hercules.service.springdatajpa;

import com.hercules.model.Container;
import com.hercules.service.ContactService;
import com.hercules.service.ContainerService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContainerJPATest {

    @Autowired
    ContainerService containerService;

    @Autowired
    ContactService contactService;

    @Test
    @Order(1)
    void crudContainerTest() {
        String testName = "test name";
        double testDailyCost = 400.5;
        Container container = new Container();

        container.setContainerName(testName);
        container.setDailyCost(testDailyCost);
        container = containerService.save(container);
        
        //Checker om den kan gemmes i DB
        assertNotNull(container.getContainerId());

        //Checker at den kan læse fra DB
        assertTrue(containerService.findAll().size() > 0);

        //Checker om den kan finde den rette container i DB baseret på ID
        assertEquals(container.getContainerId(), containerService.findById(container.getContainerId()).get().getContainerId());

        //Checker om den kan finde den rette container baseret på name
        assertEquals(container.getDailyCost(), containerService.findFirstByContainerName(testName).get().getDailyCost());

        //Checker om dailycost er opdateret i DB
        container.setDailyCost(500.5);
        container = containerService.save(container);
        assertEquals(container.getDailyCost(), containerService.findById(container.getContainerId()).get().getDailyCost());

        //Checker om den kan slette containeren fra DB
        containerService.deleteById(container.getContainerId());
        assertTrue(containerService.findById(container.getContainerId()).isEmpty());
    }

}