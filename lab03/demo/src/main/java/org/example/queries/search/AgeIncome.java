package org.example.queries.search;

import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import java.util.ArrayList;
import java.util.List;

public class AgeIncome {
    public void ageIncome(SearchParameters searchParameters, Results results) {
        List<FunctionResult> functionResults = new ArrayList<>();
        for (FunctionsParameters f : searchParameters.getFunctions()) {
            if (f.getFunction() == Funcs.SUM) {
                switch (f.getFieldName()) {
                    case "age" -> functionResults.add(Functions.ageSum(results));
                    case "income" -> functionResults.add(Functions.incomeSum(results));
                }
            }

            else if (f.getFunction() == Funcs.AVARAGE) {
                switch (f.getFieldName()) {
                    case "age" -> functionResults.add(Functions.ageAvarage(results));
                    case "income" -> functionResults.add(Functions.incomeAvarage(results));
                }
            }
        }
        results.setFunctionResults(functionResults);
    }
}
