package com.hercules.controller;

import com.hercules.model.Contact;
import com.hercules.model.Person;
import com.hercules.service.ContactService;
import com.hercules.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@Controller
public class PersonController {

    @Autowired
    ContactService contactService;

    @Autowired
    PersonService personService;

    @PostMapping("/createPerson")
    public String createPerson(@ModelAttribute Person person, @RequestParam("caseId") long caseId) {
        Contact contact;
        //check if contact object that holds persons for specific case is already in database
        if (contactService.findContactByCaseId(caseId).isPresent()) {
            contact = contactService.findContactByCaseId(caseId).get();
            if (contact.getPersons() == null) {
                contact.setPersons(new HashSet<>());
            }
            contact.getPersons().add(person);
        } else {
            //if contact doesnt exist, we create a new contact to save in the database
            contact = contactService.save(new Contact(caseId));
            contact.getPersons().add(person);
        }
        person.setContact(contact);
        personService.save(person);
        contactService.save(contact);
        return "redirect:/caseDetails/" + caseId;
    }

    //go to site where you can edit person info
    @GetMapping("/updatePerson/{personId}")
    public String updatePerson(@PathVariable("personId") Long personId, Model model) {
        if (personService.findById(personId).isPresent()) {
            model.addAttribute("person", personService.findById(personId).get());
            model.addAttribute("contactId", personService.findById(personId).get().getContact().getContactId());
        } else {
            //how did you get here?
            //as punishment, you go straight back to home
            return "redirect:/";
        }
        return "updatePerson";
    }

    //get values from previous site's form to update person in database
    @PostMapping("/updatePerson")
    public String updatePerson(@ModelAttribute Person person, @RequestParam("contactId") Long contactId) {
        //the input forms put an empty string if no text but we need it to be null for thymeleaf to work
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

    @GetMapping("/deletePerson/{personId}/{caseId}")
    public String deletePerson(@PathVariable Long personId, @PathVariable Long caseId) {
        personService.deleteById(personId);
        return "redirect:/caseDetails/" + caseId;
    }
}
