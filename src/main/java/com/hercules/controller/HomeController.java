package com.hercules.controller;

import com.hercules.model.Case;
import com.hercules.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    CaseService caseService;

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
}
