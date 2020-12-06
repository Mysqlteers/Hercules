package com.hercules.model;

import com.hercules.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TaskTest {

    @Autowired
    private TaskService ts;

    @Test
    void canCreateTaskObject() {
        HashSet<Task> subtasks = new HashSet<>();
        HashSet<S3File> files = new HashSet<>();

        Task subtask_1 = new Task("task1");

        subtask_1.setDeadline(LocalDate.now().plusDays(10).toString());
        subtasks.add(subtask_1);

        Task task = new Task("test super task",files, "2020/12/10", "2020/12/20", "", subtasks, false);
        files.add(new S3File("before-after-pictures/test_pic.jpg", true, task));


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
