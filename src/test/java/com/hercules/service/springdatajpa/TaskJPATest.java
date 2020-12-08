package com.hercules.service.springdatajpa;

import com.hercules.model.Contact;
import com.hercules.model.Task;
import com.hercules.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TaskJPATest {

    @Autowired
    TaskService taskService;


    @Test
    void canSaveAndCanFindByTaskId() {
        Task newTask = new Task();
        taskService.save(newTask);
        assert(taskService.findByTaskId(newTask.getTaskId()).isPresent());
    }

}
