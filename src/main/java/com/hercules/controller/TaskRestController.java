package com.hercules.controller;

import com.hercules.model.CalculationHelper;
import com.hercules.model.CalculationResult;
import com.hercules.model.JobType;
import com.hercules.model.TimelineHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TaskRestController
{
    @PostMapping("/api/changeMonth")
    public ResponseEntity<List<LocalDate>> calculateTotal(@RequestBody TimelineHelper body) //@RequestParam("startdate") String start
    {

        LocalDate startDate = LocalDate.parse(body.getStartDate()).plusMonths(body.getNumber());
        LocalDate endDate = startDate.plusMonths(1);

        List<LocalDate> datesList = new ArrayList<>();
        while (!startDate.isAfter(endDate)) {
            datesList.add(startDate);
            startDate = startDate.plusDays(1);
        }



        return ResponseEntity.ok(datesList);
    }
}
