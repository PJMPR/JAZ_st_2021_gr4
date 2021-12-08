package com.example.demo.controllers;

import com.example.demo.model.*;
import com.example.demo.repositories.CustomerPaymentViewRepository;
import com.example.demo.repositories.CustomerRentalViewRepository;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.jfree.util.PublicCloneable;
import org.jfree.ui.Spacer;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import com.sun.image.codec.jppg.JPEGCodec;
import static org.jfree.chart.ChartUtilities.writeChartAsJPEG;

@RestController
@RequestMapping("customers")
public class CustomerController {

    public CustomerController(CustomerRepository repository,CustomerRentalViewRepository rep1, CustomerPaymentViewRepository rep2) {
        this.repository = repository;
        this.rep1 = rep1;
        this.rep2 = rep2;
        this.chartmaker = new ChartMaker();
    }
    CustomerRepository repository;
    CustomerRentalViewRepository rep1;
    CustomerPaymentViewRepository rep2;
    ChartMaker chartmaker;
    @GetMapping
    @RequestMapping("ranking/byWatchedMovies")
    public ResponseEntity byRentedMovies(){
        List<CustomerRentalView.Response> res = rep1.findAll()
                .stream()
                .map(CustomerRentalView::getResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(res);
    }
    @GetMapping
    @RequestMapping("ranking/bySpentMoney")
    public ResponseEntity byPaidMoney() {
        List<CustomerPaymentView.Response> res = rep2.findAll()
                .stream()
                .map(CustomerPaymentView::getResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(res);
    }


    @GetMapping
    @RequestMapping("ranking/bySpentMoney.jpg")
    public void handleReports(HttpServletResponse response, @RequestParam String chart) throws IOException {
        response.setContentType("image/jpeg");
        OutputStream out = response.getOutputStream();
        writeChartAsJPEG(out , this.chartmaker.createDatasetPay(rep2.findAll()
                .stream()
                .map(CustomerPaymentView::getResponse)
                .collect(Collectors.toList()),chart)
                , 400, 400);
    }

    @GetMapping
    @RequestMapping("ranking/byWatchedMovies.jpg")
    public void handleReports2(HttpServletResponse response, @RequestParam String chart) throws IOException {
        response.setContentType("image/jpeg");
        OutputStream out = response.getOutputStream();
        writeChartAsJPEG(out , this.chartmaker.createDatasetRent(rep1.findAll()
                        .stream()
                        .map(CustomerRentalView::getResponse)
                        .collect(Collectors.toList()),chart)
                , 400, 400);
    }


    @GetMapping
    @RequestMapping("activity/rentMoviesByMonth")
    public ResponseEntity getByYearAndCustomer(@PathVariable("id") int customerid,@RequestParam int year){

        Timestamp t = Timestamp.valueOf("2021-01-10 00:00:00");
        List<Integer>list=repository.getById(customerid)
                .getRentalsByCustomerId()
                .stream()
                .filter(x->x.getRentalDate().getYear()==year)
                .map(x->x.getRentalDate().getMonth()+1)
                .collect(Collectors.toList());
        int i = 1;
        List<MonthlyStats> list2 = new ArrayList<MonthlyStats>();
        while (i < 13) {
            list2.add(new MonthlyStats(i, Collections.frequency(list,i)));
            i++;
        }
        return ResponseEntity.ok(list2);
    }

    @GetMapping
    @RequestMapping("activity/rentMoviesByMonth")
    public ResponseEntity getByYear(@RequestParam int year){

        Timestamp t = Timestamp.valueOf("2021-01-10 00:00:00");
        List<Customer> list= new ArrayList<>(repository.findAll());
        List<Integer> listrent = new ArrayList<>();
        for(Customer c: list){
            List<Integer> temp =
                    c.getRentalsByCustomerId().stream()
                    .filter(x->x.getRentalDate().getYear()==year)
                    .map(x->x.getRentalDate().getMonth()+1)
                    .collect(Collectors.toList());
            listrent.addAll(temp);
        }

        int i = 1;
        List<MonthlyStats> list2 = new ArrayList<MonthlyStats>();
        while (i < 13) {
            list2.add(new MonthlyStats(i, Collections.frequency(listrent,i)));
            i++;
        }
        return ResponseEntity.ok(list2);
    }

    @GetMapping
    @RequestMapping("activity/rentMoviesByMonth.jpg")
    public void getByYear(HttpServletResponse response, @RequestParam int year, @RequestParam String chart) throws IOException {

        Timestamp t = Timestamp.valueOf("2021-01-10 00:00:00");
        List<Customer> list= new ArrayList<>(repository.findAll());
        List<Integer> listrent = new ArrayList<>();
        for(Customer c: list){
            List<Integer> temp =
                    c.getRentalsByCustomerId().stream()
                            .filter(x->x.getRentalDate().getYear()==year)
                            .map(x->x.getRentalDate().getMonth()+1)
                            .collect(Collectors.toList());
            listrent.addAll(temp);
        }

        int i = 1;
        List<MonthlyStats> list2 = new ArrayList<MonthlyStats>();
        while (i < 13) {
            list2.add(new MonthlyStats(i, Collections.frequency(listrent,i)));
            i++;
        }
        response.setContentType("image/jpeg");
        OutputStream out = response.getOutputStream();
        writeChartAsJPEG(out , this.chartmaker.createDatasetMonthly(list2, chart), 400,400);
    }


    @GetMapping
    @RequestMapping("activity/rentMoviesByMonth.jpg")
    public void getByYearAndCustomerChart(HttpServletResponse response, @PathVariable("id") int customerid,@RequestParam int year, @RequestParam String chart) throws IOException {

        Timestamp t = Timestamp.valueOf("2021-01-10 00:00:00");
        List<Integer>list=repository.getById(customerid)
                .getRentalsByCustomerId()
                .stream()
                .filter(x->x.getRentalDate().getYear()==year)
                .map(x->x.getRentalDate().getMonth()+1)
                .collect(Collectors.toList());
        int i = 1;
        List<MonthlyStats> list2 = new ArrayList<MonthlyStats>();
        while (i < 13) {
            list2.add(new MonthlyStats(i, Collections.frequency(list,i)));
            i++;
        }
        response.setContentType("image/jpeg");
        OutputStream out = response.getOutputStream();
        writeChartAsJPEG(out , this.chartmaker.createDatasetMonthly(list2, chart), 400,400);
    }



    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity get(@PathVariable("id") int id){
        Timestamp t = Timestamp.valueOf("2021-01-10 00:00:00");
        return ResponseEntity.ok(repository.getById(id)
                .getRentalsByCustomerId()
                .stream()
                .map(Rental::getRentalId)
                .collect(Collectors.toList()));
    }
}
