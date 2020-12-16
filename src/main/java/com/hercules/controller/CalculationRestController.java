package com.hercules.controller;

import com.hercules.model.CalculationHelper;
import com.hercules.model.JobType;
import com.hercules.service.JobTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculationRestController {
    @Autowired
    JobTypeService jobTypeService;

    @PostMapping("/api/calculateTotal")
    public ResponseEntity<Double> calculateTotal(@RequestBody CalculationHelper helper) {
        Double price = 999.0;
        System.out.println("helper");
        System.out.println(helper.toString());
        if (jobTypeService.findByDescription(helper.getJobtype()).isPresent() &&
            jobTypeService.findByDescription(helper.getMaterial()).isPresent()) {
            JobType jobtype = jobTypeService.findByDescription(helper.getJobtype()).get();
            JobType material = jobTypeService.findByDescription(helper.getMaterial()).get();

            price = jobtype.getPrice() * material.getPrice() * helper.getCubicarea();
            System.out.println("helper");
            System.out.println(helper.toString());
        }

        return ResponseEntity.ok(price);
    }
}
