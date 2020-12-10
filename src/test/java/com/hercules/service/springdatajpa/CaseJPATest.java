package com.hercules.service.springdatajpa;

import com.hercules.model.Case;
import com.hercules.model.Document;
import com.hercules.model.Task;
import com.hercules.service.CaseService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CaseJPATest {
    @Autowired
    private CaseService cs;

    String location = "Vejvej 123";

    @Test
    @Order(2)
    void canFindAll() {
        assertTrue(cs.findAll().size() >= 1);
    }

    @Test
    @Order(1)
    void canSaveAndFindByLocation() {
        Case testCase = new Case("Nedriv væg", 1, location);
        Task subtask_1 = new Task("task2");
        subtask_1.addPicture("before-after-pictures/test_pic.jpg", "", false);
        testCase.addTask(subtask_1);
        Document myDoc = new Document();
        myDoc.setLocation("before-after-pictures/test_pic.jpg");
        myDoc.setDocumentName("testMyDoc");
        testCase.addDocument(myDoc);
        cs.save(testCase);
        assertTrue(cs.findById(testCase.getId()).isPresent());
    }

    @Test
    @Order(6)
    void canDeleteById() {
        int sizeBefore = cs.findAll().size();
        Case testCase = cs.findByLocation(location).get();
        cs.deleteById(testCase.getCaseId());

        assertTrue(sizeBefore > cs.findAll().size());
    }

    @Test
    @Order(5)
    void canUpdate() {
        Case testCase = cs.findByLocation(location).get();
        testCase.setStatus(2);
        cs.save(testCase);
        Case updatedCase = cs.findById(testCase.getCaseId()).get();
        assertEquals(testCase.getDescription(), updatedCase.getDescription());
        assertTrue(updatedCase.getStatus() == 2);
    }

    @Test
    @Order(3)
    void canFindById() {
        Case testCase = cs.findByLocation(location).get();
        Case otherCase = cs.findById(testCase.getCaseId()).get();
        assertEquals(testCase.getDescription(), otherCase.getDescription());
        assertEquals(testCase.getStatus(), otherCase.getStatus());
        assertEquals(testCase.getCaseId(), otherCase.getCaseId());
    }
}