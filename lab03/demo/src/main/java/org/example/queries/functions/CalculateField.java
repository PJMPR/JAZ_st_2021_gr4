package org.example.queries.functions;
import org.example.queries.search.Funcs;
import java.util.DoubleSummaryStatistics;

public abstract class CalculateField {
    protected DoubleSummaryStatistics summaryStats;
    private String field;

    public CalculateField(String fieldName, DoubleSummaryStatistics summaryStats) {
        this.summaryStats = summaryStats;
        this.field = fieldName;
    }

    public String getField() {
        return field;
    }

    public double calculate(Funcs funcs) {
        switch (funcs){
            case AVERAGE -> {
                return summaryStats.getAverage();
            }
            case SUM -> {
                return summaryStats.getSum();
            }
        }
        return 0;
    }
}