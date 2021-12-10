package com.example.demo.service;

import com.example.demo.projection.MonthIncomeRental;
import com.example.demo.visualization.BarChartGenerator;
import com.example.demo.visualization.IChartGenerator;
import com.example.demo.visualization.LinearChartGenerator;
import com.example.demo.visualization.PieChartGenerator;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.io.IOException;
import java.util.List;

public class RentalService {

    public byte[] generateRentalBarChart(String title, String xAxis, String yAxis, List<MonthIncomeRental> entryData) throws IOException {
        BarChartGenerator barChartGenerator = new BarChartGenerator();
        DefaultCategoryDataset dataset = (DefaultCategoryDataset) barChartGenerator.getDataset();

        entryData.forEach(rental -> dataset.setValue(
                (Number) rental.getRental(),
                "rentals",
                rental.getmonth()
        ));

        return barChartGenerator.generate(title, "bar", xAxis, yAxis);
    }

    public byte[] generateRentalPieChart(String title, List<MonthIncomeRental> entryData) throws IOException {
        IChartGenerator pieChartGenerator = new PieChartGenerator();
        DefaultPieDataset dataset = (DefaultPieDataset) pieChartGenerator.getDataset();

        entryData.forEach(rental -> dataset.setValue(
                rental.getmonth(),
                (Number) rental.getRental())
        );

        return pieChartGenerator.generate(title, "pie", "", "");
    }

    public byte[] generateRentalLinearChart(String title, String xAxis, String yAxis, List<MonthIncomeRental> entryData) throws IOException {
        LinearChartGenerator linearChartGenerator = new LinearChartGenerator();
        DefaultCategoryDataset dataset = (DefaultCategoryDataset) linearChartGenerator.getDataset();

        entryData.forEach(rentalStats -> dataset.setValue(
                (Number) rentalStats.getRental(),
                "",
                rentalStats.getmonth()
        ));

        return linearChartGenerator.generate(title, "linear", xAxis, yAxis);
    }
}
