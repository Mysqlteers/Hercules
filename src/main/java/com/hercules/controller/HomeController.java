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
        caseService.save(caseToUpdate);
        return "redirect:/";
    }

    @GetMapping("/updatePerson/{personId}")
    public String updatePerson(@PathVariable("personId") Long personId, Model model) {
        if (personService.findById(personId).isPresent()) {
            model.addAttribute("person", personService.findById(personId).get());
            model.addAttribute("contactId", personService.findById(personId).get().getContact().getContactId());
            System.out.println(personService.findById(personId).get().toString());
        } else {
            return "redirect:/";
        }
        return "updatePerson";
    }

    @PostMapping("/updatePerson")
    public String updatePerson(@ModelAttribute Person person, @RequestParam("contactId") Long contactId) {
        if(person.getFirstName().equals("")) {
            person.setFirstName(null);
        }
        if(person.getLastName().equals("")) {
            person.setLastName(null);
        }
        if(person.getPosition().equals("")) {
            person.setPosition(null);
        }
        if(person.getEmail().equals("")) {
            person.setEmail(null);
        }
        if(person.getPhone().equals("")) {
            person.setPhone(null);
        }
        person.setContact(contactService.findById(contactId).get());
        personService.save(person);
        Long caseId = contactService.findById(contactId).get().getCaseId();
        return "redirect:/caseDetails/" + caseId;
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

    @GetMapping("/deleteCase/{caseId}")
    public String deleteCase(@PathVariable Long caseId) {
        caseService.deleteById(caseId);
        return "redirect:/";
    }

    @GetMapping("/deletePerson/{personId}/{caseId}")
    public String deletePerson(@PathVariable Long personId, @PathVariable Long caseId) {
        personService.deleteById(personId);
        return "redirect:/caseDetails/" + caseId;
    }


}
