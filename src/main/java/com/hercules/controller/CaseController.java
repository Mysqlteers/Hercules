package com.hercules.controller;

import com.hercules.model.Task;
import com.hercules.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller()
public class CaseController
{
    @Autowired
    TaskService taskService;


    /**
     * Method hanldes the creation of a timeline, by passing the days in a month as a list, as well as
     *
     * @param taskId the id of the required task
     * @param month the month of the year
     * @param year selected year
     * @param model model for thymeleaf injection
     * @return view of the timeline
     */
    @GetMapping("/timeline/{taskId}/{month}/{year}")
    public String showTimeline(@PathVariable("taskId") int taskId,@PathVariable("month") int month, @PathVariable("year") int year, Model model) {

        LocalDate startDate = LocalDate.now().minusDays(7);
        LocalDate endDate = LocalDate.now().plusMonths(1).minusDays(7);

        if ((month<12 || month<0) && (year>0))
        {
            LocalDate date = LocalDate.of(year, month, 1);
            startDate = date;
            endDate = date.withDayOfMonth(date.lengthOfMonth());
        }
        List<LocalDate> datesList = new ArrayList<>();
        while (!startDate.isAfter(endDate)) {
            datesList.add(startDate);
            startDate = startDate.plusDays(1);
        }

        System.out.println("subtask amount  ="+taskService.findByTaskId((long) taskId).get().getSubtasksAsList().size());
        model.addAttribute("dates", datesList);
        model.addAttribute("mainTask", taskService.findByTaskId((long) taskId).get());
        model.addAttribute("subtasks", taskService.findByTaskId((long) taskId).get().getSubtasksAsList());

        return "timeline";
    }

    @PostMapping("/updateTask")
    public String updateTask(@ModelAttribute Task task){
        taskService.save(task);
        return "redirect:timeline/"+task.getTaskId()+"/"+LocalDate.now().getMonthValue()+"/"+LocalDate.now().getYear();
    }


}
