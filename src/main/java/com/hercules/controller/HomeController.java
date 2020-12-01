package com.hercules.controller;

import com.hercules.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    CaseService caseService;

    @GetMapping("/")
    public String inex(Model model) {
        model.addAttribute("cases", caseService.findAllByOrderByStatusAscCaseIdAsc());
        return "casesHome";
    }


    @GetMapping(value="/modal")
    public String index() {
        return "case-modal";
    }

}
