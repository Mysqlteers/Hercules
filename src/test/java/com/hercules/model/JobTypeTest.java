package com.hercules.model;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JobTypeTest {

    @Test
    @Order(1)
    void canCreateJobTypeObject(){
        assertNotNull(new JobType());
    }

    @Test
    @Order(2)
    void canUpdateAndReadJobTypeObject() {
        JobType jobType = new JobType();
        jobType.setDescription("Testdescription");

        assert jobType.getDescription().equals("Testdescription");
    }
}