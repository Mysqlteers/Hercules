package com.hercules.controller;

import com.hercules.model.Employee;
import com.hercules.service.EmployeeService;
import com.hercules.service.utility.S3Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    private S3Loader s3Loader = S3Loader.getInstance();

    @GetMapping("/employees")
    public String employees(Model model) {
        model.addAttribute("employees", employeeService.findAllByOrderByPositionAscFirstNameAsc());
        return "employeeList";
    }

    @PostMapping("/updateEmployee")
    public String updateCase(@ModelAttribute Employee anEmployee, @RequestParam("file") MultipartFile multipartFile) throws IOException {
        //check if user is creating new employee or editing existing
        if (anEmployee.getEmployeeId() != null) {
            //if exists, get picture from database
            Employee existingEmployee = employeeService.findById(anEmployee.getEmployeeId()).get();
            anEmployee.setPictureLocation(existingEmployee.getPictureLocation());
        }

        //the input forms put an empty string if no text but we need it to be null for thymeleaf to work
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
        if (anEmployee.getCertificates().equals("")) {
            anEmployee.setCertificates(null);
        }
        if (anEmployee.getEmail().equals("")) {
            anEmployee.setEmail(null);
        }

        anEmployee = employeeService.save(anEmployee);
        //file path of where to upload in s3 bucket
        String fileName = "employee-pictures/" + anEmployee.getEmployeeId();

        if (anEmployee.getPictureLocation() == (null) || !multipartFile.isEmpty()) {
            anEmployee.setPictureLocation(fileName);
            s3Loader.uploadImage(S3Loader.multipartFileToFile(multipartFile, "temppicture"), fileName);
            anEmployee.setPictureLocation(anEmployee.imageURL());
        }
        employeeService.save(anEmployee);
        return "redirect:/employees";
    }

    @GetMapping("/deleteEmployee/{employeeId}")
    public String deleteEmployee(@PathVariable("employeeId") long employeeId) {
        employeeService.deleteById(employeeId);
        return "redirect:/employees";
    }
}