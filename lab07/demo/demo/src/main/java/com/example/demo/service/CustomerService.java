package com.example.demo.service;

import com.example.demo.projection.CustomerByWatchedMovies;
import com.example.demo.projection.CustomerSpentMoney;
import com.example.demo.projection.RentMoviesByMonth;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.RentalRepository;
import com.example.demo.visualization.BarChartGenerator;
import com.example.demo.visualization.IChartGenerator;
import com.example.demo.visualization.PieChartGenerator;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CustomerService {
    CustomerRepository repository;
    RentalRepository rentalRepository;


    public CustomerService(CustomerRepository repository, RentalRepository rentalRepository) {
        this.repository = repository;
        this.rentalRepository = rentalRepository;
    }

    public byte[] generateWatchedMoviesCustomerPieChart(String title, List<CustomerByWatchedMovies> entryData) throws IOException {
        IChartGenerator pieChartGenerator = new PieChartGenerator();
        DefaultPieDataset dataset = (DefaultPieDataset) pieChartGenerator.getDataset();

        entryData.forEach(customer -> dataset.setValue(
                customer.getCustomer_id(),
                (Number) customer.getWatched()));

        return pieChartGenerator.generate(title, "pie", "", "");
    }

    public byte[] generateMoneySpentCustomerPieChart(String title, List<CustomerSpentMoney> entryData) throws IOException {
        IChartGenerator pieChartGenerator = new PieChartGenerator();
        DefaultPieDataset dataset = (DefaultPieDataset) pieChartGenerator.getDataset();

        entryData.forEach(customer -> dataset.setValue(customer.getCustomer_id(),
                customer.getSpent()));

        return pieChartGenerator.generate(title, "pie", "", "");
    }

    public byte[] generateWatchedMoviesCustomerBarChart (String title, String xAxis, String yAxis, List<CustomerByWatchedMovies> entryData) throws IOException {
        BarChartGenerator barChartGenerator = new BarChartGenerator();
        DefaultCategoryDataset dataset = (DefaultCategoryDataset) barChartGenerator.getDataset();

        entryData.forEach(customer -> dataset.setValue(
                (Number) customer.getWatched(),
                customer.getCustomer_id(),
                "Customers"));

        return barChartGenerator.generate(title, "bar", xAxis, yAxis);
    }

    public byte[] generateMoneySpentCustomerBarChart(String title, String xAxis, String yAxis, List<CustomerSpentMoney> entryData)  throws IOException {
        BarChartGenerator barChartGenerator = new BarChartGenerator();
        DefaultCategoryDataset dataset = (DefaultCategoryDataset) barChartGenerator.getDataset();

        entryData.forEach(customer -> dataset.setValue(
                customer.getSpent(),
                customer.getCustomer_id(),
                "Customers"));

        return barChartGenerator.generate(title, "bar", xAxis, yAxis);
    }

    public byte[] generateRentalPieChart(String title, List<RentMoviesByMonth> entryData) throws IOException {
        IChartGenerator pieChartGenerator = new PieChartGenerator();
        DefaultPieDataset dataset = (DefaultPieDataset) pieChartGenerator.getDataset();

        entryData.forEach(stats -> dataset.setValue(
                stats.getrent_month(),
                (Number) stats.getrentMovies())
        );

        return pieChartGenerator.generate(title, "pie", "", "");
    }

    public byte[] generateRentalBarChart(String title, String xAxis, String yAxis, List<RentMoviesByMonth> entryData) throws IOException {
        BarChartGenerator barChartGenerator = new BarChartGenerator();
        DefaultCategoryDataset dataset = (DefaultCategoryDataset) barChartGenerator.getDataset();

        entryData.forEach(stats -> dataset.setValue(
                (Number) stats.getrentMovies(),
                "rentals",
                stats.getrent_month()
        ));

        return barChartGenerator.generate(title, "bar", xAxis, yAxis);
    }

}
