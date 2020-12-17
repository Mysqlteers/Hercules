package com.hercules.controller;

import com.hercules.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTaskController
{

    @Autowired
    TaskService taskService;

    @PostMapping("addImageToTask")
    public void addImage()
    {

    }
}
