package com.example.demo.controllers;

import com.example.demo.model.ChartMaker;
import com.example.demo.model.MonthlyStats;
import com.example.demo.model.Payment;
import com.example.demo.model.Rental;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.PaymentsRepository;
import com.example.demo.repositories.RentalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.jfree.chart.ChartUtilities.writeChartAsJPEG;


@RestController
@RequestMapping("rentals")
public class RentalController {
    public RentalController(RentalRepository repository, PaymentsRepository rep2) {
        this.repository = repository;
        this.rep2 = rep2;
        this.chartmaker = new ChartMaker();
    }
    RentalRepository repository;
    PaymentsRepository rep2;
    ChartMaker chartmaker;
    @GetMapping
    @RequestMapping("incomebyMonth")
    public ResponseEntity get(@RequestParam Integer year){
        List<Payment> list =rep2.findAll()
                .stream()
                .filter(x->x.getPaymentDate().getYear()==year).collect(Collectors.toList());
        List<MonthlyStats> list2 = new ArrayList<MonthlyStats>();
        int i = 1;
        int c = 1;
        while (i < 13) {
            int sum = 0;
            List<Payment> temp2 =list.stream().filter(x->x.getPaymentDate().getMonth()==c).collect(Collectors.toList());
            for(Payment p:temp2){
                sum+=p.getAmount().intValue();
            }

            list2.add(new MonthlyStats(i,sum));
            i++;
        }
        return ResponseEntity.ok(list2);
    }

    @GetMapping
    @RequestMapping("income")
    public ResponseEntity get(@RequestParam String from, @RequestParam String to){
        Timestamp f = Timestamp.valueOf(from+" 00:00:00");
        Timestamp t = Timestamp.valueOf(to+" 00:00:00");
        List<Payment> list =rep2.findAll()
                .stream()
                .filter(x->x.getPaymentDate().after(f))
                .filter(x->x.getPaymentDate().before(t))
                .collect(Collectors.toList());
        List<MonthlyStats> list2 = new ArrayList<MonthlyStats>();
        int i = 1;
        int c = 1;
        int numberofmonths = (t.getYear()*12) + t.getMonth() - (f.getYear()*12) - f.getMonth();
        while (i < numberofmonths+1) {
            int sum = 0;
            List<Payment> temp2 =list.stream().filter(x->x.getPaymentDate().getMonth()==c).collect(Collectors.toList());
            for(Payment p:temp2){
                sum+=p.getAmount().intValue();
            }

            list2.add(new MonthlyStats(i,sum));
            i++;
        }
        return ResponseEntity.ok(list2);
    }

    @GetMapping
    @RequestMapping("incomebyMonth.jpg")
    public void get(HttpServletResponse response, @RequestParam Integer year, @RequestParam String char2) throws IOException {
        List<Payment> list =rep2.findAll()
                .stream()
                .filter(x->x.getPaymentDate().getYear()==year).collect(Collectors.toList());
        List<MonthlyStats> list2 = new ArrayList<MonthlyStats>();
        int i = 1;
        int c = 1;
        while (i < 13) {
            int sum = 0;
            List<Payment> temp2 =list.stream().filter(x->x.getPaymentDate().getMonth()==c).collect(Collectors.toList());
            for(Payment p:temp2){
                sum+=p.getAmount().intValue();
            }

            list2.add(new MonthlyStats(i,sum));
            i++;
        }
        response.setContentType("image/jpeg");
        OutputStream out = response.getOutputStream();
        writeChartAsJPEG(out , this.chartmaker.createDatasetToLinear(list2,char2), 400,400);
    }



    @GetMapping
    @RequestMapping("income.jpg")
    public void get(HttpServletResponse response, @RequestParam String from, @RequestParam String to, @RequestParam String char2) throws IOException {
        Timestamp f = Timestamp.valueOf(from+" 00:00:00");
        Timestamp t = Timestamp.valueOf(to+" 00:00:00");
        List<Payment> list =rep2.findAll()
                .stream()
                .filter(x->x.getPaymentDate().after(f))
                .filter(x->x.getPaymentDate().before(t))
                .collect(Collectors.toList());
        List<MonthlyStats> list2 = new ArrayList<MonthlyStats>();
        int i = 1;
        int c = 1;
        int numberofmonths = (t.getYear()*12) + t.getMonth() - (f.getYear()*12) - f.getMonth();
        while (i < numberofmonths+1) {
            int sum = 0;
            List<Payment> temp2 =list.stream().filter(x->x.getPaymentDate().getMonth()==c).collect(Collectors.toList());
            for(Payment p:temp2){
                sum+=p.getAmount().intValue();
            }
            list2.add(new MonthlyStats(i,sum));
            i++;
        }
        response.setContentType("image/jpeg");
        OutputStream out = response.getOutputStream();
        writeChartAsJPEG(out , this.chartmaker.createDatasetToLinear(list2,char2), 400,400);
    }



}
