package com.pjwstk.sakila.diagnostics.service.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MonitoringController {
    @GetMapping("/monitoring")
    public void getServiceStatus(){
        //Lista zarejestrowanych w monitoringu serwisów

    }

    @PostMapping("/monitoring/register")
    public void post(){
//        Rejestracja nowego serwisu lub aktualizacja stanu już zarejestrowanego
    }
}
