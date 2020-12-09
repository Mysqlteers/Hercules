package com.hercules.service.springdatajpa;

import com.hercules.model.Case;
import com.hercules.model.Document;
import com.hercules.service.CaseService;
import com.hercules.service.DocumentService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DocumentJPATest {
    @Autowired
    DocumentService ds;

    @Autowired
    CaseService cs;

    Long testCaseId = (long) 1;

    @Test
    @Order(2)
    public void canFindAll(){ assertTrue(ds.findAll().size() >= 1);  }

    @Test
    @Order(1)
    public void canSaveAndCanFindByCaseId(){
        Document newDocument = new Document();

        ArrayList<Case> k = new ArrayList<>(cs.findAll());
        Case myCase = k.get(0);
        myCase.addDocument("helloWorld", "before-after-pictures/test_pic.jpg");
        cs.save(myCase);

        assertTrue(ds.findDocumentByCaseId(testCaseId).isPresent());
    }

    @Test
    @Order(4)
    void canDeleteById(){
        int sizeBefore = ds.findAll().size();
        ds.deleteById(ds.findDocumentByCaseId(testCaseId).get().getDocumentId());
        assertTrue(sizeBefore > ds.findAll().size());
    }

    @Test
    @Order(3)
    void canFindById(){
        Document document = ds.findDocumentByCaseId(testCaseId).get();
        Document otherDocument = ds.findById(document.getDocumentId()).get();
//        assertEquals(document.getDocumentId(), otherDocument.getSuperCase());
        assertEquals(document.getDocumentId(), otherDocument.getDocumentId());
    }


}

