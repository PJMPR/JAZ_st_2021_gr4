package com.example.demo.repository;

import com.example.demo.controller.Timetable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableRepo extends CrudRepository<Timetable,Integer> {
    Timetable findById(int id);
}
