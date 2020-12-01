package com.hercules.service.springdatajpa;

import com.hercules.model.Case;
import com.hercules.repository.CaseRepository;
import com.hercules.service.CaseService;
import org.hibernate.cache.spi.entry.CacheEntryStructure;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        cs.save(newCase);
        assertTrue(cs.findByLocation(location).isPresent());
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