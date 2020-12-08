package com.hercules.controller;

import com.hercules.model.Case;
import com.hercules.model.S3File;
import com.hercules.model.Task;
import com.hercules.service.CaseService;
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

    @Autowired
    CaseService caseService;

    @PostMapping(value="/handleTask", params={"delete=Slet"} )
    public String deleteTask(@RequestParam(value = "taskId") long id)
    {
        Task task = taskService.findByTaskId(id).get();
        taskService.deleteById(id);
        if (task.getSuperTask()!=null)
            return "redirect:/timeline/"+task.getSuperTask().getTaskId()+"/"+ LocalDate.now().getMonthValue()+"/"+LocalDate.now().getYear();
        else
            return "redirect:/caseDetails/"+task.getCase().getCaseId();
    }


    @PostMapping(value="/handleTask", params={"create=Ny"} )
    public String CreateTask(@RequestParam Map<String, String> params){


        params.entrySet().forEach(p ->{
            System.out.println(p);
        });

        Task task = new Task();
        task.setName(params.get("name")+"- ny opgave");
        task.setTask_start_date(LocalDate.now().toString());
        task.setDeadline(LocalDate.now().toString());

        if (!params.get("taskId").isEmpty()) {
            System.out.println("with supertask");
            try {
                Task superTask = taskService.findByTaskId(Long.parseLong(params.get("taskId"))).get();
                superTask.addTask(task);
                taskService.save(superTask);
            }catch (Exception e){e.printStackTrace();}
        }
        else {
            try {
                Case Case = caseService.findById(Long.parseLong(params.get("CaseId"))).get();
                Case.addTask(task);
                caseService.save(Case);
            } catch (Exception e) {e.printStackTrace();}
        }

        if (task.getSuperTask()!=null)
            return "redirect:/timeline/"+task.getSuperTask().getTaskId()+"/"+ LocalDate.now().getMonthValue()+"/"+LocalDate.now().getYear();
        else
            return "redirect:/caseDetails/"+params.get("CaseId");
    }

    @PostMapping(value="/handleTask", params={"update=Gem"})
    public String updateTask(@RequestParam Map<String, String> params){

        Task task = taskService.findByTaskId(Long.parseLong(params.get("taskId"))).get();
        try {
            if (params.get("isDone").equals("true"))
                task.setDone(true);
            else
                task.setDone(false);
        }catch (Exception e){e.printStackTrace();}
        task.setName(params.get("name"));
        task.setTask_start_date(params.get("task_start_date"));
        task.setDeadline(params.get("deadline"));
        task.setEst_time(params.get("est_time"));
        task.setDescription(params.get("description"));

        taskService.save(task);
        if (task.getSuperTask()!=null)
            return "redirect:/timeline/"+task.getSuperTask().getTaskId()+"/"+ LocalDate.now().getMonthValue()+"/"+LocalDate.now().getYear();
        else
            return "redirect:/caseDetails/"+task.getCase().getCaseId();
    }
}