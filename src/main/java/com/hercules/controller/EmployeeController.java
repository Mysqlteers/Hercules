package com.hercules.controller;

import com.hercules.model.Employee;
import com.hercules.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public String employees(Model model) {
        model.addAttribute("employees", employeeService.findAllByOrderByPositionAscFirstNameAsc());
        for (Employee e: employeeService.findAllByOrderByPositionAscFirstNameAsc()) {
            System.out.println(e);
        }
        return "employeeList";
    }

}
