package com.example.demo.repositories;

import com.example.demo.model.Customer;
import com.example.demo.projection.CustomerByWatchedMovies;
import com.example.demo.projection.CustomerSpentMoney;
import com.example.demo.projection.RentMoviesByMonth;
import com.example.demo.projection.RentMoviesByMonthCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(nativeQuery = true, value =
            "SELECT customer.customer_id, first_name, last_name, SUM(payment.amount) AS spent " +
                    "FROM customer JOIN payment ON customer.customer_id = payment.customer_id " +
                    "GROUP BY customer.customer_id ORDER BY spent DESC limit 10")
    List<CustomerSpentMoney> get10CustomersBySpentMoney();

    @Query(nativeQuery = true, value =
            "SELECT customer.customer_id, first_name, last_name, COUNT(payment.amount) AS watched " +
                    "FROM customer JOIN payment ON customer.customer_id = payment.customer_id " +
                    "GROUP BY customer.customer_id ORDER BY watched DESC limit 10")
    List<CustomerByWatchedMovies> get10CustomersByMostMoviesWatched();

    @Query(nativeQuery = true, value = "SELECT month(rental_date) AS month, count(rental_id) AS rentMovies " +
            "FROM rental GROUP BY month")
    List<RentMoviesByMonth> getRentMoviesByMonth();

    @Query(nativeQuery = true, value = "SELECT month(rental_date) AS month, COUNT(rental_id) AS rentMovies " +
                                        "FROM rental " +
                                        "WHERE customer_id = :#{#customer_id} " +
                                        "GROUP BY month")
    List<RentMoviesByMonthCustomer> getRentMoviesByMonthForCustomer(@Param("customer_id") final int customerId);

    @Query(nativeQuery = true, value = "select month(rental_date) as month, count(rental_id) as rentMovies " +
                                         "from rental " +
                                        "where year(rental_date) = :#{#year} and customer_id = :#{#customer_id} " +
                                        "group by month")
    List<RentMoviesByMonthCustomer> getRentMoviesByMonthForYearAndCustomer(@Param("customer_id") final int customerId, @Param("year") final int year);

    @Query(nativeQuery = true, value = "SELECT month(rental_date) AS rent_month, COUNT(rental_id) AS rentMovies " +
            "                           FROM rental " +
            "                           WHERE year(rental_date) = :#{#year}" +
            "                           GROUP BY rent_month")
    List<RentMoviesByMonth> getRentMoviesByMonthForYear(@Param("year") int year);



}
