package com.example.demo.service;

import com.example.demo.controller.Calculator;
import com.example.demo.controller.Installment;
import com.example.demo.controller.Timetable;
import com.example.demo.repository.InstallmentRepo;
import java.util.List;

public class InstallmentService {
    InstallmentRepo installmentRepository;
    Calculator installmentCalculator;

    public InstallmentService(InstallmentRepo installmentRepository, Calculator installmentCalculator) {
        this.installmentRepository = installmentRepository;
        this.installmentCalculator = installmentCalculator;
    }

    public List<Installment> calculateInstallments(Timetable timetable) {
        return installmentCalculator.calculateInstalments(timetable);
    }

    public void saveInstallments(Installment installment) {
        installmentRepository.save(installment);
    }
}
