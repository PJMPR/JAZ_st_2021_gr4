package com.example.demo.controllers;

import com.example.demo.projection.CustomerByWatchedMovies;
import com.example.demo.projection.CustomerSpentMoney;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {

    public CustomerController(CustomerRepository repository, CustomerService service) {
        this.repository = repository; this.service = service;
    }

    CustomerRepository repository;
    CustomerService service;

    @GetMapping("ranking/bySpentMoney")
    public ResponseEntity<List<CustomerSpentMoney>> getBySpentMoney() {
        return ResponseEntity.ok(repository.get10CustomersBySpentMoney());
    }

    @GetMapping("ranking/bySpentMoney.jpg")
    public ResponseEntity<byte[]> getBySpentMoneyChart(@RequestParam(value = "chart") String chart) {
        if(chart.equals("pie")){
            try {
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                        .body(service.generateMoneySpentCustomerPieChart(
                                "Customers by money spent",
                                repository.get10CustomersBySpentMoney()));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if (chart.equals("bar")){
            try {
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                        .body(service.generateMoneySpentCustomerBarChart(
                                "Customers by money spent",
                                "",
                                "Money spent",
                                repository.get10CustomersBySpentMoney()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("ranking/byWatchedMovies")
    public ResponseEntity<List<CustomerByWatchedMovies>> getByWatchedMovies() {
        return ResponseEntity.ok(repository.get10CustomersByMostMoviesWatched());
    }

    @GetMapping("ranking/byWatchedMovies.jpg")
    public ResponseEntity<byte[]> getByWatchedMoviesChart(@RequestParam(value = "chart") String chart) {
        if(chart.equals("pie")){
            try {
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                        .body(service.generateWatchedMoviesCustomerPieChart(
                                "Customers by most movies watched",
                                repository.get10CustomersByMostMoviesWatched()));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if (chart.equals("bar")){
            try {
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                        .body(service.generateWatchedMoviesCustomerBarChart(
                                "Customers by most movies watched",
                                "",
                                "Money spent",
                                repository.get10CustomersByMostMoviesWatched()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("activity/rentMoviesByMonth")
    public ResponseEntity<List<?>> getRentMoviesByMonth(@RequestParam(value = "year") Integer year,
                                                                                @RequestParam(value = "customer_id") Integer id
    ) {
        if(id != null) return ResponseEntity.ok(repository.getRentMoviesByMonthForYearAndCustomer(id, year));
        return ResponseEntity.ok(repository.getRentMoviesByMonthForYear(year));
    }

    @GetMapping("activity/rentMoviesByMonth.jpg")
    public ResponseEntity<byte[]> getRentMoviesByMonthChart(@RequestParam(value = "chart") String chart,
                                                            @RequestParam(value = "year") Integer year,
                                                            @RequestParam(value = "id", required = false) Integer id
    )
    {
        if(chart.equals("pie")){
            try {
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                        .body(service.generateRentalPieChart(
                                "Rent movies by month",
                                repository.getRentMoviesByMonth()));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else if (chart.equals("bar")){
            try {
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                        .body(service.generateRentalBarChart(
                                "Rent movies by month",
                                "",
                                "",
                                repository.getRentMoviesByMonth()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST);
    }









}
