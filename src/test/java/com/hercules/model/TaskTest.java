package com.hercules.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TaskTest {

    @Test
    void canCreateTaskObject(){
        Task task = new Task();
        assertNotNull(task);
    }

    @Test
    void canUpdateAndReadTask(){
        Long taskId = (long) 1;
        Task task = new Task();
        task.setTask_id(taskId);
        assertEquals(task.getTask_id(), taskId);
    }
}
