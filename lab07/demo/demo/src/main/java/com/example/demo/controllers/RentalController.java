package com.example.demo.controllers;


import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.RentalRepository;
import com.example.demo.service.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("rental")
public class RentalController {

    public RentalController(RentalRepository repository, RentalService service) {
        this.repository = repository;
        this.service = service;
    }

    RentalRepository repository;
    RentalService service;

    @GetMapping("incomebyMonth")
    public ResponseEntity<List<?>> getIncomeByMonth(@RequestParam(value = "year") Integer year
    ) {
        return ResponseEntity.ok(repository.getMonthIncomeForYear(year));
    }

    @GetMapping("incomebyMonth.jpg")
    public ResponseEntity<byte[]> getIncomeByMonthChart(@RequestParam(value = "chart") String chart, @RequestParam(value = "year") Integer year) throws IOException {
        switch (chart) {
            case "linear":
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                        .body(service.generateRentalLinearChart(
                                "Income by month",
                                "Months",
                                "Income",
                                repository.getMonthIncomeForYear(year)));
            case "bar":
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                        .body(service.generateRentalBarChart(
                                "Income by month",
                                "Months",
                                "Income",
                                repository.getMonthIncomeForYear(year)));
            case "pie":
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                        .body(service.generateRentalPieChart(
                                "Income by month",
                                repository.getMonthIncomeForYear(year)));
        }
        return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
    }
    @GetMapping("income")
    public ResponseEntity<List<?>> getIncomeByMonth(@RequestParam(value = "from") Timestamp from, @RequestParam(value = "to") Timestamp to,
                                                    @RequestParam(value = "chart", required = false) String chart
    ) {
        return ResponseEntity.ok(null);
    }
}
