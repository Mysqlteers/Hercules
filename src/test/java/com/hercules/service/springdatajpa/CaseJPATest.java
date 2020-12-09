package com.hercules.service.springdatajpa;

import com.hercules.model.Case;
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
        Case newCase = new Case("Nedriv væg", 1, location);
        Task subtask_1 = new Task("task2");
        subtask_1.addPicture("before-after-pictures/test_pic.jpg", "", false);
        newCase.addTask(subtask_1);
        newCase.addDocument("testdoc", "before-after-pictures/test_pic.jpg" ); // Added from
        newCase.addDocument("testdoc2", "before-after-pictures/test_pic.jpg" );
        newCase.addDocument("testdoc3", "before-after-pictures/test_pic.jpg" );
        newCase.addDocument("testdoc4", "before-after-pictures/test_pic.jpg" );
        newCase.addDocument("testdoc5", "before-after-pictures/test_pic.jpg" );
        cs.save(newCase);
        assertTrue(cs.findById(newCase.getId()).isPresent());
    }

    @Test
    @Order(5)
    void canDeleteById() {
        int sizeBefore = cs.findAll().size();
        cs.deleteById(cs.findByLocation(location).get().getCaseId());
        assertTrue(sizeBefore > cs.findAll().size());
    }

    @Test
    @Order(4)
    void canUpdate() {
        Case newCase = cs.findByLocation(location).get();
        newCase.setStatus(2);
        cs.save(newCase);
        Case updatedCase = cs.findById(newCase.getCaseId()).get();
        assertEquals(newCase.getDescription(), updatedCase.getDescription());
        assertTrue(updatedCase.getStatus() == 2);
    }

    @Test
    @Order(3)
    void canFindById() {
        Case newCase = cs.findByLocation(location).get();
        Case otherCase = cs.findById(newCase.getCaseId()).get();
        assertEquals(newCase.getDescription(), otherCase.getDescription());
        assertEquals(newCase.getStatus(), otherCase.getStatus());
        assertEquals(newCase.getCaseId(), otherCase.getCaseId());
    }
}