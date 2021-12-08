package com.example.demo.repositories;

import com.example.demo.model.CustomerPaymentView;
import com.example.demo.model.CustomerRentalView;
import com.example.demo.model.MonthlyStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerPaymentViewRepository extends JpaRepository<CustomerPaymentView, Integer> {
    @Query("SELECT new com.example.demo.model.MonthlyStats(MONTH(payment.payment_date), payment.ammount) from payment GROUP BY ")
    List<MonthlyStats> MonthlySales();
}
