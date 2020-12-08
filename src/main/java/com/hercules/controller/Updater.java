package com.hercules.controller;

import com.hercules.model.Task;
import com.hercules.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class Updater {
    
    @Autowired
    TaskService taskService;

    @PostMapping(value="/updateTask", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public String updateTask(@RequestBody Task task){
        System.out.println(task.getSuperTask().getTaskId());
        taskService.save(task);
        return "timeline/"+task.getSuperTask().getTaskId()+"/"+ LocalDate.now().getMonthValue()+"/"+LocalDate.now().getYear();
    }
}
