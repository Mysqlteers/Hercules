package com.hercules.controller;

import com.hercules.model.*;
import com.hercules.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hercules.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class CaseController {

    @Autowired
    TaskService taskService;

    @Autowired
    CaseService caseService;

    @Autowired
    ContactService contactService;

    @Autowired
    PersonService personService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ContainerService containerService;


    /**
     * Method handles the creation of a timeline.
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
        if (taskService.findByTaskId((long) taskId).isPresent()) {
            model.addAttribute("superTask", taskService.findByTaskId((long) taskId).get());
            model.addAttribute("dates", datesList);
        }
        return "modals/timeline";
    }


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("cases", caseService.findAllByOrderByStatusAscCaseIdAsc());
        return "casesHome";
    }

    @PostMapping("/updateCase")
    public String updateCase(@ModelAttribute Case aCase) {
        //the input forms put an empty string if no text but we need it to be null for thymeleaf to work
        if (aCase.getLocation().equals("")) {
            aCase.setLocation(null);
        }
        if (aCase.getDescription().equals("")) {
            aCase.setDescription(null);
        }
        if (aCase.getCaseStartDate().equals("")) {
            aCase.setCaseStartDate(null);
        }
        aCase = caseService.save(aCase);
        if (contactService.findContactByCaseId(aCase.getCaseId()).isEmpty()) {
            contactService.save(new Contact(aCase.getCaseId()));
        }

        return "redirect:/caseDetails/" + aCase.getCaseId();
    }

    @GetMapping("/deleteCase/{caseId}")
    public String deleteCase(@PathVariable Long caseId) {
        caseService.deleteById(caseId);
        Contact contact = contactService.findContactByCaseId(caseId).get();
        for (Employee e : contact.getEmployees()) {
            contact.removeEmployee(e);
        }
        for (Tool t : contact.getTools()) {
            contact.removeTool(t);
        }
        contactService.deleteById(contactService.findContactByCaseId(caseId).get().getContactId());
        return "redirect:/";
    }

    @GetMapping("/caseDetails/{caseId}")
    public String caseDetails(@PathVariable("caseId") long caseId, Model model){
        if(caseService.findById(caseId).isPresent()) {
            model.addAttribute("viewCase", caseService.findById(caseId).get());

            if (contactService.findContactByCaseId(caseId).isPresent()) {
                Contact contact = contactService.findContactByCaseId(caseId).get();
                List<Employee> attachedEmployees = employeeService.findAllByContacts_contactIdOrderByPositionAscFirstNameAsc(contact.getContactId());
                List<Employee> allEmployees = employeeService.findAllByOrderByPositionAscFirstNameAsc();
                allEmployees.removeAll(attachedEmployees);
                model.addAttribute("contactlist", personService.findAllByContactOrderByPositionAscFirstNameAsc(contact));
                model.addAttribute("allEmployees", allEmployees);
                model.addAttribute("attachedEmployees", attachedEmployees);
                model.addAttribute("containerlist", containerService.findAllByContactOrderByContainerIdDesc(contact));
            } else {
                model.addAttribute("contactlist", new HashSet<Person>());
                model.addAttribute("employee", new HashSet<Employee>());
            }

            //Adding
            LocalDate startDate = LocalDate.now().minusDays(7);
            LocalDate endDate = LocalDate.now().plusMonths(1).minusDays(7);
            List<LocalDate> datesList = new ArrayList<>();
            while (!startDate.isAfter(endDate)) {
                datesList.add(startDate);
                startDate = startDate.plusDays(1);
            }
            model.addAttribute("dates", datesList);
            model.addAttribute("mainTask", caseService.findById((long) caseId).get());
            if (contactService.findContactByCaseId(caseId).isPresent())
                model.addAttribute("contact", contactService.findContactByCaseId(caseId).get());


            return "caseDetails";
        } else {
            model.addAttribute("errorCode", 0);
            return "genericErrorPage";
        }
    }
}
