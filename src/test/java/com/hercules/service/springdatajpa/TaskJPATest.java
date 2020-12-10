package com.hercules.service.springdatajpa;

import com.hercules.model.Contact;
import com.hercules.model.Task;
import com.hercules.service.TaskService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskJPATest {

    @Autowired
    TaskService taskService;

    String TASK_NAME = "Test task";
    String TEST_DEADLINE = "Test deadline";

    @Test
    @Order(1)
    void canSaveAndCanFindByTaskId() {
        Task newTask = new Task(TASK_NAME);
        taskService.save(newTask);
        assert(taskService.findByTaskId(newTask.getTaskId()).isPresent());
    }

    @Test
    @Order(2)
    void canUpdateTask() {
        Task newTask = taskService.findByName(TASK_NAME).get();
        newTask.setDeadline(TEST_DEADLINE);
        newTask = taskService.save(newTask);

        assert(taskService.findByTaskId(newTask.getTaskId()).get().getDeadline().equals(TEST_DEADLINE));
    }

    @Test
    @Order(3)
    void canDeleteTask() {
        taskService.deleteById(taskService.findByName(TASK_NAME).get().getTaskId());
        assert(taskService.findByName(TASK_NAME).isEmpty());
    }

}
