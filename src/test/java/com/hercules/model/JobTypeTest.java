package com.hercules.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hercules.controller.CalculationRestController;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@RunWith(SpringRunner.class)
//@WebMvcTest
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