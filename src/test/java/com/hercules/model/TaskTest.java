package com.hercules.model;

import com.hercules.service.TaskService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskTest {

    @Autowired
    private TaskService ts;

    @Test
    @Order(1)
    void canCreateTaskObject() {
        HashSet<S3File> files = new HashSet<>();
        files.add(new S3File("before-after-pictures/test_pic.jpg", true));

        HashSet<Task> subtasks = new HashSet<>();

        Task subtask_1 = new Task("task1");

        subtask_1.setDeadline(LocalDate.now().plusDays(10).toString());
        subtask_1.setPictures(files);
        subtasks.add(subtask_1);

        S3File mitBillede = new S3File("before-after-pictures/test_pic.jpg", true);
        Task task = new Task("test super task",files, "2020/12/10", "2020/12/20", "",subtasks, false);
        task.getPictures().add(mitBillede);
        mitBillede.setTask(task);

        ts.save(task);

        assert ts.findByTaskId(task.getTaskId()).get()!=null;
    }

    @Test
    @Order(2)
    void canUpdateAndReadTask() {
        Long taskId = (long) 1;
        Task task = new Task();
        task.setTaskId(taskId);
        assertEquals(task.getTaskId(), taskId);
    }
}
