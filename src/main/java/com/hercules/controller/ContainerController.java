package com.hercules.controller;

import com.hercules.model.Container;
import com.hercules.service.ContactService;
import com.hercules.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ContainerController {
    @Autowired
    ContainerService containerService;

    @Autowired
    ContactService contactService;

    @PostMapping("/updateContainer/{viewCaseId}")
    public String updateContainer(@ModelAttribute Container container, @PathVariable("viewCaseId") Long caseId, @RequestParam("isPickedUpCheckbox") Optional<String> checkbox) {
        container.setContact(contactService.findContactByCaseId(caseId).get());
        if (checkbox.isPresent()) {
            container.setPickedUp(true);
        } else {
            container.setPickedUp(false);
        }
        containerService.save(container);
        return "redirect:/caseDetails/" + caseId;
    }

    @GetMapping("/deleteContainer/{containerId}/{viewCaseId}")
    public String deleteContainer(@PathVariable("containerId") Long containerId, @PathVariable("viewCaseId") Long caseId) {
        containerService.deleteById(containerId);
        return "redirect:/caseDetails/" + caseId;
    }
}
