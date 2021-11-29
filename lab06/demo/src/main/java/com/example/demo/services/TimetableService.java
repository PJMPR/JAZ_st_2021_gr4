package com.example.demo.services;

import com.example.demo.credit.CreditConstant;
import com.example.demo.credit.CreditDecreasing;
import com.example.demo.models.Calculation;
import com.example.demo.models.Timetable;
import com.example.demo.repositories.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.models.InstallmentType.CONSTANT;
import static com.example.demo.models.InstallmentType.DECREASING;

@Service
public class TimetableService {
    private final TimetableRepository timetableRepository;

    @Autowired
    public TimetableService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    public void addNewTimetable(Calculation calculation) {
        switch (calculation.getInstallmentType()){
            case CONSTANT: {
                timetableRepository.saveAll(new CreditConstant(calculation).constantRateCalculation());
                break;
            }
            case DECREASING: {
                timetableRepository.saveAll(new CreditDecreasing(calculation).decreasingRateCalculation());
                break;
            }
        }
    }

    public List<Timetable> getTimetable(long id) {
        return timetableRepository.findAllById(id);
    }
}
