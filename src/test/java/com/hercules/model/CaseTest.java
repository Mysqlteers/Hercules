package com.hercules.model;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CaseTest
{



    @Test
    @Order(1)
    public void canCreateCaseObject(){
        HashSet<Task> subtasks = new HashSet<>();
        Task subtask_1 = new Task("task1");
        subtask_1.addPicture("before-after-pictures/test_pic.jpg", "", false);

        Case newCase = new Case();
        newCase.addTask(subtask_1);

        assertNotNull(newCase);
    }

    @Test
    @Order(2)
    public void canUpdateAndReadCase(){
        String newDesc = "New description";
        Case newCase = new Case();
        newCase.setDescription(newDesc);
        assertEquals(newCase.getDescription(), newDesc);
    }
}