package org.example.functions;

import org.example.model.Person;
import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;

class FunctionCalculationPatterns {

    protected FunctionResult calculateSumOfAge(Results results) {
        FunctionResult result = new FunctionResult();
        result.setFunction(Funcs.AVERAGE);
        result.setFieldName("income");
        result.setValue(results.getItems().stream()
                .map(Person::getAge).reduce(0, Integer::sum));
        return result;
    }
    protected FunctionResult calculateAverageOfAge(Results results) {

        FunctionResult result = new FunctionResult();
        result.setFunction(Funcs.AVERAGE);
        result.setFieldName("age");
        result.setValue(results.getItems().stream()
                .mapToDouble(Person::getAge)
                .average()
                .orElse(Double.NaN));
        return result;
    }
    protected FunctionResult calculateSumOfIncome(Results results) {
        FunctionResult result = new FunctionResult();
        result.setFunction(Funcs.AVERAGE);
        result.setFieldName("income");
        result.setValue(results.getItems().stream()
                .map(Person::getIncome).reduce(0.0, Double::sum));
        return result;
    }
    protected FunctionResult calculateAverageOfIncome(Results results) {
        FunctionResult result = new FunctionResult();

        result.setFunction(Funcs.AVERAGE);
        result.setFieldName("income");
        result.setValue(results.getItems().stream()
                .mapToDouble(Person::getIncome)
                .average()
                .orElse(Double.NaN));
        return result;
    }

}
