package com.hercules.controller;

import com.hercules.model.Container;
import com.hercules.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContainerController {
    @Autowired
    ContainerService containerService;

    @PostMapping("/updateContainer")
    public String updateContainer(@ModelAttribute Container container, @RequestParam("viewCaseId") Long caseId) {
        System.out.println(container.toString());
        return "redirect:/caseDetails/" + caseId;
    }
}
