package com.hercules.model;

import com.hercules.service.CaseService;
import com.hercules.service.ContactService;
import com.hercules.service.ToolService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ToolTest {

    @Autowired
    ContactService cs;

    @Autowired
    ToolService ts;

    @Test
    @Order(1)
    void canSaveAndFindByIdAndUpdateAndDelete() {
        // can save and find
        Tool tool = new Tool();
        tool.model= "testModel";

        tool = ts.save(tool);

        assertTrue(ts.findById(tool.getToolId()).isPresent());

        //update
        tool.model= "OtherTestModel";
        ts.save(tool);

        assertTrue(ts.findById(tool.getToolId()).get().model.equals("OtherTestModel"));

        //delete
        ts.deleteById(tool.getToolId());

        assertFalse(ts.findById(tool.getToolId()).isPresent());
    }

    @Test
    @Order(2)
    public void OneContactHasManyTools()
    {
        Contact testContact = new Contact();
        testContact = cs.save(testContact);
        Tool tool = new Tool();
        Tool tool2 = new Tool();
        tool.model= "testModel";


        testContact.addTool(tool);
        testContact.addTool(tool2);

        tool = ts.save(tool);
        tool2 = ts.save(tool2);


        cs.save(testContact);

        testContact=cs.findById(testContact.getContactId()).get();

        System.out.println(testContact.getTools().size());

        assert testContact.getTools().size()==2;
    }

    @Test
    @Order(3)
    public void canDeleteContactWithoutDeletingTool()
    {
        Contact testContact = new Contact();
        testContact = cs.save(testContact);
        Tool tool = new Tool();
        Tool tool2 = new Tool();
        tool.model= "testModel";

        tool = ts.save(tool);
        tool2 = ts.save(tool2);

        testContact.addTool(tool);
        testContact.addTool(tool2);

        tool = ts.save(tool);
        tool2 = ts.save(tool2);

        testContact = cs.save(testContact);

        cs.deleteById(testContact.getContactId());

        System.out.println("ID 1 ="+tool.getToolId());
        System.out.println("ID 2 ="+tool2.getToolId());
        System.out.println("TestContact ="+testContact.getContactId());


        assertFalse(ts.findById(tool.getToolId()).isPresent());
        assertFalse(ts.findById(tool2.getToolId()).isPresent());
        assertFalse(cs.findById(testContact.getContactId()).isPresent());
    }

}