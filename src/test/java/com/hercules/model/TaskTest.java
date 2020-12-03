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

        Task subtask_1 = new Task("task1");

        subtask_1.setEst_time("10-days");
        subtask_1.setBeforePictures(files);

        Task subtask_2 = new Task("task2");
        subtask_2.setEst_time("1-day");


        Task subtask_3 = new Task("task3");
        subtask_3.setEst_time("4-days");

        subtasks.add(subtask_1);
        subtasks.add(subtask_2);
        subtasks.add(subtask_3);

        Task task = new Task("test super task",files, "2020/12/10", "2020/12/20", "", subtasks, false);

        ts.save(task);

        assert ts.findByTaskId(task.getTaskId()).get()!=null;
    }

    @Test
    void canUpdateAndReadTask() {
        Long taskId = (long) 1;
        Task task = new Task();
        task.setTaskId(taskId);
        assertEquals(task.getTaskId(), taskId);
    }
}
