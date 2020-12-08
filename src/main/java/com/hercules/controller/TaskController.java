package com.hercules.controller;

import com.hercules.model.S3File;
import com.hercules.model.Task;
import com.hercules.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@Controller
public class TaskController {

    @Autowired
    TaskService taskService;


    @PostMapping(value="/handleTask", params={"delete=Slet"} )
    public String deleteTask(@RequestParam(value = "taskId") long id)
    {

        Task task = taskService.findByTaskId(id).get();
        taskService.deleteById(id);
        return "redirect:/timeline/"+task.getSuperTask().getTaskId()+"/"+ LocalDate.now().getMonthValue()+"/"+LocalDate.now().getYear();
    }


    @PostMapping(value="/handleTask", params={"create=Ny"} )
    public String CreateTask(@RequestParam Map<String, String> params){


        Task superTask = taskService.findByTaskId(Long.parseLong(params.get("taskId"))).get();//new Task(params.get("name"), null,  params.get("task_start_date"),  params.get("deadline"),  params.get("est_time"), null, done);

        Task task = new Task();

        System.out.println("Is done"+params.get("isDone"));
        if (params.get("isDone").equals("true"))
            task.setDone(true);
        else
            task.setDone(false);

        task.setName(params.get("name")+"- ny opgave");
        task.setTask_start_date(LocalDate.now().toString());
        task.setDeadline(params.get(LocalDate.now().toString()));
        task.setEst_time(params.get("1 dag"));
        task.setDescription("under opgave til opgave "+params.get("name"));
        task.setSuperTask(superTask);


        taskService.save(task);
        return "redirect:/timeline/"+task.getSuperTask().getTaskId()+"/"+ LocalDate.now().getMonthValue()+"/"+LocalDate.now().getYear();
    }

    @PostMapping(value="/handleTask", params={"update=Gem"})
    public String updateTask(@RequestParam Map<String, String> params){

        Task task = taskService.findByTaskId(Long.parseLong(params.get("taskId"))).get();//new Task(params.get("name"), null,  params.get("task_start_date"),  params.get("deadline"),  params.get("est_time"), null, done);

        System.out.println("Is done"+params.get("isDone"));
        if (params.get("isDone").equals("true"))
            task.setDone(true);
        else
            task.setDone(false);

        task.setName(params.get("name"));
        task.setTask_start_date(params.get("task_start_date"));
        task.setDeadline(params.get("deadline"));
        task.setEst_time(params.get("est_time"));
        task.setDescription(params.get("description"));

        taskService.save(task);
        return "redirect:/timeline/"+task.getSuperTask().getTaskId()+"/"+ LocalDate.now().getMonthValue()+"/"+LocalDate.now().getYear();
    }
}