package com.example.demo.model;

public class MonthlyStats {
    public MonthlyStats(int m, int r){
        this.month=m;
        this.rentmovies=r;
    }
    final int month;
    final int rentmovies;

    public int getMonth() {
        return month;
    }

    public int getRentmovies() {
        return rentmovies;
    }
}
