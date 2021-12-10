package com.example.demo.controllers;

import com.example.demo.projection.CustomerByWatchedMovies;
import com.example.demo.projection.CustomerSpentMoney;
import com.example.demo.projection.RentMoviesByMonth;
import com.example.demo.projection.RentMoviesByMonthCustomer;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    CustomerRepository repository;

    @GetMapping("ranking/bySpentMoney")
    public ResponseEntity<List<CustomerSpentMoney>> getBySpentMoney() {
        return ResponseEntity.ok(repository.get10CustomersBySpentMoney());
    }

    @GetMapping("ranking/bySpentMoney.jpg")
    public ResponseEntity<byte[]> getBySpentMoneyChart(@RequestParam(value = "chart") String chart) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("ranking/byWatchedMovies")
    public ResponseEntity<List<CustomerByWatchedMovies>> getByWatchedMovies() {
        return ResponseEntity.ok(repository.get10CustomersByMostMoviesWatched());
    }

    @GetMapping("ranking/byWatchedMovies.jpg")
    public ResponseEntity<byte[]> getByWatchedMoviesChart(@RequestParam(value = "chart") String chart) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("activity/rentMoviesByMonth")
    public ResponseEntity<List<?>> getRentMoviesByMonth(@RequestParam(value = "year") Integer year,
                                                                                @RequestParam(value = "customer_id") Integer id
    ) {
        if(id != null) return ResponseEntity.ok(repository.getRentMoviesByMonthForYearAndCustomer(id, year));
        return ResponseEntity.ok(repository.getRentMoviesByMonthForYear(year));
    }

    @GetMapping("activity/rentMoviesByMonth.jpg")
    public ResponseEntity<List<?>> getRentMoviesByMonthChart(@RequestParam(value = "chart") String chart,
                                                             @RequestParam(value = "year") Integer year,
                                                             @RequestParam(value = "id", required = false) Integer id
    )
    {
        return ResponseEntity.ok(null);
    }









}
