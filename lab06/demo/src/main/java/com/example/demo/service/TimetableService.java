package com.example.demo.service;

import com.example.demo.controller.Timetable;
import com.example.demo.repository.TimetableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimetableService {

    TimetableRepo timetableRepository;

    @Autowired
    public TimetableService(TimetableRepo timetableRepository) {
        this.timetableRepository = timetableRepository;
    }

    public int insertData(Timetable timetable) {
        timetableRepository.save(timetable);
        return timetable.getId();
    }

    public Timetable getTimetable(int id) {
        return timetableRepository.findById(id);
    }
}
