package com.example.demo.repositories;



import com.example.demo.model.Customer;
import com.example.demo.model.MonthlyStats;
import com.example.demo.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentsRepository extends JpaRepository<Payment, Integer> {

    @Query(value="SELECT rental.customer_id, customer.first_name, customer.last_name, COUNT(*) as count FROM rental INNER JOIN customer ON rental.customer_id=customer.customer_id GROUP BY rental.customer_id ORDER BY count desc LIMIT 10;", nativeQuery = true)
    List<MonthlyStats> topRenters();

    @Query(value="SELECT payment.customer_id, customer.first_name, customer.last_name, SUM(payment.amount) as payments FROM payment INNER JOIN customer ON payment.customer_id=customer.customer_id GROUP BY payment.customer_id ORDER BY payments desc LIMIT 10;", nativeQuery = true)
    List<Object> topPayers();

}