package com.example.demo.repositories;

import com.example.demo.model.CustomerRentalView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRentalViewRepository extends JpaRepository<CustomerRentalView, Integer> {

}
