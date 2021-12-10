package com.example.demo.controllers;


import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.RentalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("rental")
public class RentalController {

    public RentalController(RentalRepository repository) {
        this.repository = repository;
    }

    RentalRepository repository;

    @GetMapping("incomebyMonth")
    public ResponseEntity<List<?>> getIncomeByMonth(@RequestParam(value = "year") Integer year
    ) {
        return ResponseEntity.ok(repository.getMonthIncomeForYear(year));
    }

    @GetMapping("incomebyMonth.jpg")
    public ResponseEntity<List<?>> getIncomeByMonthChart(@RequestParam(value = "chart") String chart, @RequestParam(value = "year") Integer year)
    {
        return ResponseEntity.ok(null);
    }
    @GetMapping("income")
    public ResponseEntity<List<?>> getIncomeByMonth(@RequestParam(value = "from") Timestamp from, @RequestParam(value = "to") Timestamp to,
                                                    @RequestParam(value = "chart", required = false) String chart
    ) {
        return ResponseEntity.ok(null);
    }
}
