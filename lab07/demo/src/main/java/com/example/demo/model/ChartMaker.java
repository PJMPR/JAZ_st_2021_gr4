package com.example.demo.model;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import static org.jfree.chart.ChartUtilities.writeChartAsJPEG;


public class ChartMaker {
    public ChartMaker() {
    }

    public JFreeChart createDatasetPay(List<CustomerPaymentView.Response> list, String chart2) throws IOException {
        if (Objects.equals(chart2, "bar")) {
            String var2 = "Spent Money";
            DefaultCategoryDataset data = new DefaultCategoryDataset();
            list.forEach(x -> data.addValue(x.getSpent(), var2, x.getCustomer().getName() + " " + x.getCustomer().getSurname()));
            return ChartFactory.createBarChart(
                    "Top 10 Clients",
                    "Name", "Money spent",
                    data, PlotOrientation.VERTICAL,
                    false, true, false);
        } else if (Objects.equals(chart2, "pie")) {
            DefaultPieDataset data2 = new DefaultPieDataset();
            list.forEach(x -> data2.setValue(x.getCustomer().getName() + " " + x.getCustomer().getSurname(), x.getSpent()));
            return ChartFactory.createPieChart(
                    "Pie chart",
                    data2,
                    true, true, false);

        }
        String var2 = "Spent Money";
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        list.forEach(x -> data.addValue(x.getSpent(), var2, x.getCustomer().getName() + " " + x.getCustomer().getSurname()));
        return ChartFactory.createBarChart(
                "Top 10 Clients",
                "Name", "Money spent",
                data, PlotOrientation.VERTICAL,
                false, true, false);
    }

    public JFreeChart createDatasetRent(List<CustomerRentalView.Response> list, String chart2) throws IOException {
        if (Objects.equals(chart2, "bar")) {
            String var2 = "Spent Money";
            DefaultCategoryDataset data = new DefaultCategoryDataset();
            list.forEach(x -> data.addValue(x.getWatched(), var2, x.getCustomer().getName() + " " + x.getCustomer().getSurname()));
            return ChartFactory.createBarChart(
                    "Top 10 Clients",
                    "Name", "Money spent",
                    data, PlotOrientation.VERTICAL,
                    false, true, false);
        } else if (Objects.equals(chart2, "pie")) {
            DefaultPieDataset data2 = new DefaultPieDataset();
            list.forEach(x -> data2.setValue(x.getCustomer().getName() + " " + x.getCustomer().getSurname(), x.getWatched()));
            return ChartFactory.createPieChart(
                    "Pie chart",
                    data2,
                    true, true, false);

        }
        String var2 = "Spent Money";
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        list.forEach(x -> data.addValue(x.getWatched(), var2, x.getCustomer().getName() + " " + x.getCustomer().getSurname()));
        return ChartFactory.createBarChart(
                "Top 10 Clients",
                "Name", "Money spent",
                data, PlotOrientation.VERTICAL,
                false, true, false);
    }

    public JFreeChart createDatasetMonthly(List<MonthlyStats> list, String chart2) throws IOException {
        if (Objects.equals(chart2, "bar")) {
            String var2 = "Spent Money/Rented Movies";
            DefaultCategoryDataset data = new DefaultCategoryDataset();
            list.forEach(x -> data.addValue(x.getRentmovies(), var2, String.valueOf(x.getMonth())));
            return ChartFactory.createBarChart(
                    "By month",
                    "Month", "Money spent/Rented",
                    data, PlotOrientation.VERTICAL,
                    false, true, false);
        } else if (Objects.equals(chart2, "pie")) {
            DefaultPieDataset data2 = new DefaultPieDataset();

            list.forEach(x -> data2.setValue(String.valueOf(x.getMonth()), x.getMonth()));
            return ChartFactory.createPieChart(
                    "Pie chart",
                    data2,
                    true, true, false);

        }
        String var2 = "Spent Money/RentedMovies";
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        list.forEach(x -> data.addValue(x.getRentmovies(), var2, String.valueOf(x.getMonth())));
        return ChartFactory.createBarChart(
                "By Month",
                "Month", "Money spent/Rented",
                data, PlotOrientation.VERTICAL,
                false, true, false);
    }

    public JFreeChart createDatasetToLinear(List<MonthlyStats> list, String chart2) throws IOException {

        String var2 = "EarnedCash";
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        list.forEach(x -> data.addValue(x.getRentmovies(), var2, String.valueOf(x.getMonth())));
        return ChartFactory.createLineChart(
                "By month",
                "Month", "Money earned",
                data, PlotOrientation.VERTICAL,
                false, true, false);


    }
}