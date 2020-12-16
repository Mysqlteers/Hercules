package com.hercules.controller;

import com.hercules.model.CalculationHelper;
import com.hercules.model.CalculationResult;
import com.hercules.model.JobType;
import com.hercules.service.JobTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;

@RestController
public class CalculationRestController {
    @Autowired
    JobTypeService jobTypeService;

    @PostMapping("/api/calculateTotal")
    public ResponseEntity<CalculationResult> calculateTotal(@RequestBody CalculationHelper helper) {
        CalculationResult result = new CalculationResult();
        double priceWithMarkup = 0.0;
        double priceWithoutMarkup = 0.0;
        if (jobTypeService.findByDescription(helper.getJobtype()).isPresent() &&
            jobTypeService.findByDescription(helper.getMaterial()).isPresent()) {
            JobType jobtype = jobTypeService.findByDescription(helper.getJobtype()).get();
            JobType material = jobTypeService.findByDescription(helper.getMaterial()).get();

            priceWithoutMarkup = (jobtype.getPrice() * material.getPrice() * helper.getCubicarea());
            double markup = (helper.getMarkup() + 100) / 100;
            priceWithMarkup = priceWithoutMarkup * markup;
        }

        DecimalFormat df = new DecimalFormat("0.00");
        result.setPriceWithMarkup(df.format(priceWithMarkup));
        result.setPriceWithoutMarkup(df.format(priceWithoutMarkup));
        return ResponseEntity.ok(result);
    }
}
