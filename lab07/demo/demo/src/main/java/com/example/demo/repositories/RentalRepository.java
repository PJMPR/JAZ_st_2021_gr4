package com.example.demo.repositories;

import com.example.demo.model.Customer;
import com.example.demo.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {
    @Query(nativeQuery = true, value = "select month(payment_date) as month, count(amount) as income " +
                                        "from payment " +
                                        "where year(payment_date) = :#{#year} " +
                                        "group by month")
    List<?> getMonthIncomeForYear(@Param("year") final int year);

    @Query("SELECT CAST (DATE_FORMAT(date(p.paymentDate), '%Y-%m-01')) as month, SUM(p.amount) as income " +
            "FROM Payment as p " +
            "WHERE p.paymentDate BETWEEN :from AND :to " +
            "GROUP BY month ORDER BY month")
    List<?> getIncomeFromTo(Timestamp from, Timestamp to);


}
