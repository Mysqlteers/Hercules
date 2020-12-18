package com.hercules.controller;

import com.hercules.model.Contact;
import com.hercules.model.Document;
import com.hercules.model.Employee;
import com.hercules.service.ContactService;
import com.hercules.service.DocService;
import com.hercules.service.EmployeeService;
import com.hercules.service.utility.S3Loader;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Controller
public class EmployeeController {
    @Autowired
    DocService docService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ContactService contactService;

    private S3Loader s3Loader = S3Loader.getInstance();

    @GetMapping("/employees")
    public String employees(Model model) {
        model.addAttribute("employees", employeeService.findAllByOrderByPositionAscFirstNameAsc());
        for (Employee e : employeeService.findAllByOrderByPositionAscFirstNameAsc()) {
            System.out.println(e.getCertificates().size() + "  " + e.getDocumentsAsList().size());
        }
        return "employeeList";
    }

    @PostMapping("/updateEmployee")
    public String updateCase(@ModelAttribute Employee anEmployee, @RequestParam("file") MultipartFile multipartFile, @RequestParam(value = "certificateFiles", required = false) List<MultipartFile> certificateFiles) throws IOException {
        System.out.println(certificateFiles.toString());
        //check if user is creating new employee or editing existing
        if (anEmployee.getEmployeeId() != null) {
            //if exists, get picture from database
            Employee existingEmployee = employeeService.findById(anEmployee.getEmployeeId()).get();
            anEmployee.setContacts(existingEmployee.getContacts());
            anEmployee.setPictureLocation(existingEmployee.getPictureLocation());
        }

        //TODO refactor employee-modal elvis operators to return null instead of ""
        //the input forms put an empty string if no text but we need it to be null for database to work
        if (anEmployee.getPosition().equals("")) {
            anEmployee.setPosition(null);
        }
        if (anEmployee.getFirstName().equals("")) {
            anEmployee.setFirstName(null);
        }
        if (anEmployee.getLastName().equals("")) {
            anEmployee.setLastName(null);
        }
        if (anEmployee.getPhone().equals("")) {
            anEmployee.setPhone(null);
        }
//        if (anEmployee.getCertificates().equals("")) {
//            anEmployee.setCertificates(null);
//        }
        if (anEmployee.getEmail().equals("")) {
            anEmployee.setEmail(null);
        }

        //get the employeeId from database into object
        anEmployee = employeeService.save(anEmployee);

        //file path of where to upload in s3 bucket
        String fileName = "employee-pictures/" + anEmployee.getEmployeeId();
        String certificateName;

        if (anEmployee.getCertificates() == null) {
            anEmployee.setCertificates(new HashSet<>());
        }

        for(MultipartFile m : certificateFiles) {
        if (!m.getName().equals("")) {
            certificateName = "employee-certificates/" + anEmployee.getEmployeeId() + "/" + m.getOriginalFilename();
            s3Loader.uploadFile(m, certificateName);
            System.out.println(certificateName);
            Document document = new Document(certificateName, certificateName, anEmployee);
            System.out.println(document.toString());
            System.out.println(document.getUrl());
            if (document.getOriginalFilename() != null || document.getOriginalFilename().equals("")) {
                anEmployee.addDocument(document);
                docService.save(document);
            }
            System.out.println(anEmployee.getCertificates().size());
        }
        }
        System.out.println("Efter for loop: " + anEmployee.getCertificates().size());

        //we check if employee doesnt have a picture OR the input file exists
        //in which case, we want to update the employee picture
        if (anEmployee.getPictureLocation() == (null) || !multipartFile.isEmpty()) {
            anEmployee.setPictureLocation(fileName);
            s3Loader.uploadImage(S3Loader.multipartFileToFile(multipartFile, "temppicture"), fileName);
            anEmployee.setPictureLocation(anEmployee.imageURL());
        }
        System.out.println("Efter pictures bliver uploaded " + anEmployee.getCertificates().size());
        employeeService.save(anEmployee);
        System.out.println("Efter employee er gemt " + anEmployee.getCertificates().size());
        return "redirect:/employees";
    }

    @GetMapping("/deleteEmployee/{employeeId}")
    public String deleteEmployee(@PathVariable("employeeId") long employeeId) {
        employeeService.deleteById(employeeId);
        return "redirect:/employees";
    }

    @RequestMapping("/addEmployeesToCase/{caseId}")
    public String addEmployeesToCase(@RequestParam("employeeIds") List<Long> pickedEmployees, @PathVariable("caseId") Long caseId) {
        Contact contact = contactService.findContactByCaseId(caseId).get();
        for (Long id: pickedEmployees) {
                if (id != null) {
                    Employee e = employeeService.findById(id).get();
                    contact.addEmployee(e);
                }
        }
        contactService.save(contact);
        return "redirect:/caseDetails/" + caseId;
    }

    @GetMapping("/removeEmployeeFromCase/{employeeId}/{caseId}")
    public String removeEmployeeFromCase(@PathVariable("employeeId") Long employeeId, @PathVariable("caseId") Long caseId) {
        Contact contact = contactService.findContactByCaseId(caseId).get();
        contact.removeEmployee(employeeService.findById(employeeId).get());
        contactService.save(contact);
        return "redirect:/caseDetails/" + caseId;
    }
}
