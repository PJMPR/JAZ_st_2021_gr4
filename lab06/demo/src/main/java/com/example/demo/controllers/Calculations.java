package com.example.demo.controllers;

import com.example.demo.models.Calculation;
import com.example.demo.services.CalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/credit")
public class Calculations {

    private final CalculationService calculationService;

    @Autowired
    public Calculations(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping(path = "/calculations")
    public long registerNewCalculation(@RequestBody Calculation calculation) {
        return calculationService.addNewCalculation(calculation);
    }

    @GetMapping(path = "/calculations")
    public ResponseEntity getCalculation(@RequestParam long id) {
        return ResponseEntity.ok().body(calculationService.getCalculation(id));
    }

}