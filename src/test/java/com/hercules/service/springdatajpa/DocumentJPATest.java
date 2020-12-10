package com.hercules.service.springdatajpa;

import com.hercules.model.Document;
import com.hercules.service.CaseService;
import com.hercules.service.DocService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DocumentJPATest {

    @Autowired
    private DocService ds;

    Document myDoc;

    @Test
    void CanCreateAndFindByIDAndThenDelete() {
        myDoc = new Document();
        int elements = ds.findAll().size();
        myDoc= ds.save(myDoc);
        assert  ds.findAll().size() > elements;
        ds.deleteById(myDoc.getDocumentId());
        assert ds.findAll().size() == elements;
    }
}