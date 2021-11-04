package org.example.queries.results;

import org.example.queries.search.Funcs;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.SearchParameters;
import java.util.ArrayList;
import java.util.List;

public class AgeResult {
    AverageSum calculations = new AverageSum();
    public void ageIncome(SearchParameters searchParameters, Results results) {
        List<FunctionResult> functionResults = new ArrayList<>();

        for (FunctionsParameters f : searchParameters.getFunctions()) {

            if (f.getFunction() == Funcs.SUM) {
                switch (f.getFieldName()) {
                    case "age" -> functionResults.add(calculations.ageSum(results));
                    case "income" -> functionResults.add(calculations.incomeSum(results));
                }
            }

            else if (f.getFunction() == Funcs.AVARAGE) {
                switch (f.getFieldName()) {
                    case "age" -> functionResults.add(calculations.ageAvg(results));
                    case "income" -> functionResults.add(calculations.incomeAvg(results));
                }
            }
        }
        results.setFunctionResults(functionResults);
    }
}