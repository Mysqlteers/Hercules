package com.hercules.model;

import com.hercules.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TaskTest {

    @Autowired
    private TaskService ts;

    @Test
    void canCreateTaskObject(){
        HashSet<S3File> files= new HashSet<>();
        files.add(new S3File("before-after-pictures/test_pic.jpg"));

        Task task = new Task(files, null, "today", "tommow", null, null, null);
        task.setBeforePictures(files);
        ts.save(task);




        ts.findAll().forEach(item -> {
            System.out.println(item.getBeforePictures().toArray().length);
        });


    }

    @Test
    void canUpdateAndReadTask(){
        Long taskId = (long) 1;
        Task task = new Task();
        task.setTaskId(taskId);
        assertEquals(task.getTaskId(), taskId);
    }
}
