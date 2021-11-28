package com.example.demo.repository;

import com.example.demo.controller.Installment;
import org.springframework.data.repository.CrudRepository;

public interface InstallmentRepo extends CrudRepository<Installment,Integer> {
    Installment findById(int id);
}
