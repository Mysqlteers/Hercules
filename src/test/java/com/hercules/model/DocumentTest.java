package com.hercules.model;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DocumentTest {
    @Test
    @Order(1)
    public void canCreateDocumentObject(){
        Document newDocument = new Document();
        assertNotNull(newDocument);
    }

    @Test
    @Order(2)
    public void canUpdateAndReadDocument(){
        String newName = "New Name";
        Document newDocument = new Document();
        newDocument.setDocumentName(newName);
        assertEquals(newDocument.getDocumentName(), newDocument);
    }

}
