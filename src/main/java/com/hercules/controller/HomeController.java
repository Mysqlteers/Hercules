package com.hercules.controller;

import com.hercules.model.Case;
import com.hercules.model.Task;
import com.hercules.service.TaskService;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController
{
    @Autowired
    TaskService taskService;
    @GetMapping
    public String index(Model model) {


        model.addAttribute("mainTask", taskService.findByTaskId((long) 1).get());


        String start = "2020-12-01";
        String end = "2020-12-28";
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        List<LocalDate> datesList = new ArrayList<>();
        while (!startDate.isAfter(endDate)) {
            datesList.add(startDate);
            startDate = startDate.plusDays(1);
        }


        model.addAttribute("dates", datesList);

        return "timeline";
    }

}
