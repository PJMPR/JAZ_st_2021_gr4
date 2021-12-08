package com.example.demo.repositories;

import com.example.demo.model.Customer;
import com.example.demo.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
    @Query(value="SELECT rental.customer_id COUNT(*) as count FROM rental GROUP BY rental.customer_id ORDER BY count desc LIMIT 10;", nativeQuery = true)
    ArrayList topRenters();

}
