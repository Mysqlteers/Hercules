package com.hercules.service.springdatajpa;

import com.hercules.controller.CalculationRestController;
import com.hercules.model.CalculationHelper;
import com.hercules.model.CalculationResult;
import com.hercules.model.JobType;
import com.hercules.service.JobTypeService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JobTypeJPATest {
    @Autowired
    JobTypeService js;

    @Test
    @Order(1)
    void jobTypeCrudTest() {
        String testDescription = "Test description";
        double testPrice = 400.5;
        JobType jobType = new JobType();

        jobType.setDescription(testDescription);
        jobType.setPrice(testPrice);
        jobType = js.save(jobType);

        //Checker om den kan gemmes i DB
        assertNotNull(jobType.getJobTypeId());

        //Checker at den kan læse fra DB
        assertTrue(js.findAll().size() > 0);

        //Checker om den kan finde den rette jobType i DB baseret på ID
        assertEquals(jobType.getJobTypeId(), js.findById(jobType.getJobTypeId()).get().getJobTypeId());

        //checker om vi kan finde jobtype efter description
        assertEquals(jobType.getPrice(), js.findByDescription(testDescription).get().getPrice());

        //Checker om price er opdateret i DB
        jobType.setPrice(500.5);
        jobType = js.save(jobType);
        assertEquals(jobType.getPrice(), js.findById(jobType.getJobTypeId()).get().getPrice());

        //Checker om den kan slette jobtype fra DB
        js.deleteById(jobType.getJobTypeId());
        assertTrue(js.findById(jobType.getJobTypeId()).isEmpty());
    }

    @Test
    public void createEmployeeAPI() {
        CalculationRestController crc = new CalculationRestController(js);
        String jobtypedescription = "testjobtype";
        String materialdescription = "testmaterial";
        JobType jobtype;
        JobType material;

//        check if test job type or test material already exists in DB
//        in case of earlier failed test where it didnt get to delete
        if (js.findByDescription(jobtypedescription).isEmpty()) {
            jobtype = new JobType();
            jobtype.setPrice(1);
            jobtype.setDescription(jobtypedescription);
            js.save(jobtype);
        } else {
            jobtype = js.findByDescription(jobtypedescription).get();
        }
        if (js.findByDescription(materialdescription).isEmpty()) {
            material = new JobType();
            material.setPrice(100);
            material.setDescription(materialdescription);
            js.save(material);
        } else {
            material = js.findByDescription(materialdescription).get();
        }

//        this is object that sends info to the restcontroller
        CalculationHelper helper = new CalculationHelper(jobtype.getDescription(),
                material.getDescription(), 1, 100);
//        this helper is the one that holds the result from the restcontroller
        ResponseEntity<CalculationResult> result = crc.calculateTotal(helper);

        assertEquals(result.getBody().getPriceWithMarkup(), "200.00");
        assertEquals(result.getBody().getPriceWithoutMarkup(), "100.00");

        js.deleteById(jobtype.getJobTypeId());
        js.deleteById(material.getJobTypeId());
    }
}