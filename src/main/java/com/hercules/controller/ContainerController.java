package com.hercules.controller;

import com.hercules.model.Container;
import com.hercules.service.ContactService;
import com.hercules.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ContainerController {
    @Autowired
    ContainerService containerService;

    @Autowired
    ContactService contactService;

    @PostMapping("/updateContainer/{viewCaseId}")
    public String updateContainer(@ModelAttribute Container container, @PathVariable("viewCaseId") Long caseId) {
        container.setContact(contactService.findContactByCaseId(caseId).get());
        containerService.save(container);
        return "redirect:/caseDetails/" + caseId;
    }
}
