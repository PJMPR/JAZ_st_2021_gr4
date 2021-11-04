package org.example.functions;

import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;


public class ageGetResult {
    
    averageSumFunctions calculations = new averageSumFunctions();
    
    public void ageIncome(SearchParameters searchParams, Results results) {
        List<FunctionResult> functionResults = new ArrayList<>();

        for (FunctionsParameters f : searchParams.getFunctions()) {
            if (f.getFunction() == Funcs.SUM) {
                switch (f.getFieldName()) {
                    case "age":
                        functionResults.add(calculations.ageSum(results));
                        break;
                    case "income":
                        functionResults.add(calculations.incomeSum(results));
                        break;
                }
            } else if (f.getFunction() == Funcs.AVERAGE) {
                switch (f.getFieldName()) {
                    case "age" :
                        functionResults.add(calculations.ageAvg(results));
                        break;
                    case "income" :
                        functionResults.add(calculations.incomeAvg(results));
                        break;
                }
            }
        }
        results.setFunctionResults(functionResults);
    }
}
