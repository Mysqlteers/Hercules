package com.hercules.controller;

import com.hercules.model.Case;
import com.hercules.model.Contact;
import com.hercules.model.Person;
import com.hercules.service.CaseService;
import com.hercules.service.ContactService;
import com.hercules.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@Controller
public class HomeController {
    @Autowired
    CaseService caseService;

    @Autowired
    ContactService contactService;

    @Autowired
    PersonService personService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("cases", caseService.findAllByOrderByStatusAscCaseIdAsc());
        return "casesHome";
    }

    @PostMapping("/updateCase")
    public String updateCase(@ModelAttribute Case caseToUpdate) {
        //the input forms put an empty string if no text but we need it to be null for thymeleaf to work
        if (caseToUpdate.getLocation().equals("")) {
            caseToUpdate.setLocation(null);
        }
        if (caseToUpdate.getDescription().equals("")) {
            caseToUpdate.setDescription(null);
        }
        if (caseToUpdate.getCaseStartDate().equals("")) {
            caseToUpdate.setCaseStartDate(null);
        }
        caseService.save(caseToUpdate);
        return "redirect:/";
    }

    @GetMapping("/deleteCase/{caseId}")
    public String deleteCase(@PathVariable Long caseId) {
        caseService.deleteById(caseId);
        return "redirect:/";
    }

    @GetMapping("/caseDetails/{caseId}")
    public String caseDetails(@PathVariable("caseId") long caseId, Model model){
        model.addAttribute("viewCase", caseService.findById(caseId).get());

        if (contactService.findContactByCaseId(caseId).isPresent()) {
            Contact contact = contactService.findContactByCaseId(caseId).get();
            model.addAttribute("contactlist", personService.findAllByContactOrderByPositionAscFirstNameAsc(contact));
        } else {
            model.addAttribute("contactlist", new HashSet<Person>());
        }
        return "caseDetails";
    }
}
