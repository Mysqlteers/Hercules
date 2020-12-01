package com.hercules.controller;

import com.hercules.model.Case;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
    @GetMapping
    public String index(Model model) {

        Case newcase = new Case();
newcase.setDescription("im a very goodest boi");
        newcase.setCaseId(new Long(100));
        model.addAttribute("newCase", newcase); //needed for modal to create 'new case'
        return "index";
    }
}
