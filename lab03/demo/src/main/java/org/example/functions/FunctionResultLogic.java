package org.example.functions;

import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.SearchParameters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FunctionResultLogic {

    public void checkWhichFunctionNeeded(SearchParameters searchParameters, Results results) {

        FunctionCalculationPatterns calculations = new FunctionCalculationPatterns();

        List<FunctionResult> values = new ArrayList<>();

        for (FunctionsParameters param : searchParameters.getFunctions()) {

            if (sumOfAgeRequired(param)){
                values.add(calculations.calculateSumOfAge(results));
            }
            else if (sumOfIncomeRequired(param)){
                values.add(calculations.calculateSumOfIncome(results));
            }
            else if (avgOfAgeRequired(param)){
                values.add(calculations.calculateAverageOfAge(results));
            }
            else if (avgOfIncomeRequired(param)){
                values.add(calculations.calculateAverageOfIncome(results));
            }
        }
        results.setFunctionResults(values);
    }

    private boolean avgOfIncomeRequired(FunctionsParameters param) {
        return (paramFunctionEquals(param,Funcs.AVERAGE) && paramFieldNameEquals(param,"income"));
    }
    private boolean avgOfAgeRequired(FunctionsParameters param) {
        return (paramFunctionEquals(param,Funcs.AVERAGE) && paramFieldNameEquals(param,"age"));
    }
    private boolean sumOfIncomeRequired(FunctionsParameters param) {
        return paramFunctionEquals(param,Funcs.SUM) && paramFieldNameEquals(param,"income");
    }
    private boolean sumOfAgeRequired(FunctionsParameters param) {
        return paramFunctionEquals(param,Funcs.SUM) && paramFieldNameEquals(param,"age");
    }
    public boolean paramFunctionEquals(FunctionsParameters param, Funcs function){
        return param.getFunction()==function;
    }
    public boolean paramFieldNameEquals(FunctionsParameters param, String fiedName){
        return Objects.equals(param.getFieldName(), fiedName);
    }

}
