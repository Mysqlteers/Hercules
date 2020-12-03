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
    public String inex(Model model) {
        model.addAttribute("cases", caseService.findAllByOrderByStatusAscCaseIdAsc());
        model.addAttribute("createCase", new Case());
//        for (Case c: caseService.findAll()) {
//            System.out.println(c.getCaseStartDate());
//        }
        return "casesHome";
    }

    @GetMapping(value="/modal")
    public String index() {
        return "case-modal";
    }

    @PostMapping("/updateCase")
    public String updateCase(@ModelAttribute Case caseToUpdate) {
        if (caseToUpdate.getLocation().equals("")) {
            caseToUpdate.setLocation(null);
        }
        if (caseToUpdate.getDescription().equals("")) {
            caseToUpdate.setDescription(null);
        }
        if (caseToUpdate.getCaseStartDate().equals("")) {
            caseToUpdate.setCaseStartDate(null);
        }
        System.out.println("controller calling");
        caseService.save(caseToUpdate);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/createCase")
    public String createCase() {
        return "createCase";
    }

    @PostMapping("/createCase")
    public String createCase(@ModelAttribute Case newCase) {
        caseService.save(newCase);
        return "redirect:/";
    }

    @GetMapping("/caseDetails/{caseId}")
    public String caseDetails(@PathVariable("caseId") long caseId, Model model){
        model.addAttribute("viewCase", caseService.findById(caseId).get());

        if (contactService.findContactByCaseId(caseId).isPresent()) {
            model.addAttribute("contactlist", contactService.findContactByCaseId(caseId).get().getPersons());
            System.out.println(contactService.findContactByCaseId(caseId).get().getPersons().toString());
        } else {
            model.addAttribute("contactlist", new HashSet<Person>());
        }
        return "caseDetails";
    }

    @GetMapping("/createContact/{caseId}")
    public String createContact(@PathVariable("caseId") long caseId, Model model) {
        model.addAttribute("caseId", caseId);
        return "createContact";
    }

    @PostMapping("/createContact")
    public String createContact(@ModelAttribute Person person, @RequestParam("caseId") long caseId) {
        Contact contact;
        if (contactService.findContactByCaseId(caseId).isPresent()) {
            contact = contactService.findContactByCaseId(caseId).get();
            if (contact.getPersons() == null) {
                contact.setPersons(new HashSet<>());
            }
            contact.getPersons().add(person);
        } else {
            contact = contactService.save(new Contact(caseId));
            contact.getPersons().add(person);
        }
        person.setContact(contact);
        personService.save(person);
        contactService.save(contact);
        return "redirect:/caseDetails/" + caseId;
    }
}
