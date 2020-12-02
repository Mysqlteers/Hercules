package com.hercules.model;

import com.hercules.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskTest {

    @Autowired
    private TaskService ts;

    @Test
    void canCreateTaskObject() {
        HashSet<S3File> files = new HashSet<>();
        files.add(new S3File("before-after-pictures/test_pic.jpg", true));

        HashSet<Task> subtasks = new HashSet<>();

        Task Subtask_1 = new Task();
        Subtask_1.setEst_time("10");
        Subtask_1.setBeforePictures(files);

        Task Subtask_2 = new Task();
        Subtask_2.setEst_time("20");

        Task Subtask_3 = new Task();
        Subtask_3.setEst_time("30");

        subtasks.add(Subtask_1);
        subtasks.add(Subtask_2);
        subtasks.add(Subtask_3);

        Task task = new Task(files, "today", "tommow", null, subtasks, null);

        ts.save(task);


    }

    @Test
    void canUpdateAndReadTask() {
        Long taskId = (long) 1;
        Task task = new Task();
        task.setTaskId(taskId);
        assertEquals(task.getTaskId(), taskId);
    }
}
