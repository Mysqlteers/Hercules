package com.hercules.controller;

import com.hercules.service.JobTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CalculatorController {
    @Autowired
    JobTypeService jobTypeService;

    @GetMapping("/calculator")
    public String calculator(Model model) {
        model.addAttribute("jobTypeList", jobTypeService.findAllByMaterialOrderByDescriptionAsc(false));
        model.addAttribute("materialList", jobTypeService.findAllByMaterialOrderByDescriptionAsc(true));
        return "calculator";
    }

    @GetMapping("/jobTypeList")
    public String jobTypeList(Model model) {
        model.addAttribute("jobTypeList", jobTypeService.findAllByMaterialOrderByDescriptionAsc(false));
        model.addAttribute("materialList", jobTypeService.findAllByMaterialOrderByDescriptionAsc(true));
        return "jobTypeList";
    }

    @GetMapping("/deleteJobType/{jobTypeId}")
    public String deleteJobType(@PathVariable("jobTypeId") Long jobTypeId) {
        jobTypeService.deleteById(jobTypeId);
        return "redirect:/jobTypeList";
    }
}
