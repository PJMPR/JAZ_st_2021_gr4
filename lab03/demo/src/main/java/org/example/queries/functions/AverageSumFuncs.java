package org.example.queries.functions;

import org.example.model.Person;
import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;

import java.util.stream.Collectors;

public class AverageSumFuncs {
    protected FunctionResult ageAvg(Results results) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFunction(Funcs.AVARAGE);
        functionResult.setFieldName("age");
        functionResult.setValue(
                results.getItems()
                        .stream()
                        .collect(Collectors.averagingInt(Person::getAge)));
        return functionResult;
    }

    protected FunctionResult incomeAvg(Results results) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFunction(Funcs.AVARAGE);
        functionResult.setFieldName("income");
        functionResult.setValue(
                results.getItems()
                        .stream()
                        .collect(Collectors.averagingDouble(Person::getIncome)));
        return functionResult;
    }

    protected FunctionResult ageSum(Results results) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFunction(Funcs.SUM);
        functionResult.setFieldName("income");
        functionResult.setValue(
                results.getItems()
                        .stream()
                        .mapToInt(Person::getAge)
                        .sum());
        return functionResult;
    }

    protected FunctionResult incomeSum(Results results) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFunction(Funcs.SUM);
        functionResult.setFieldName("income");
        functionResult.setValue(
                results.getItems()
                        .stream()
                        .mapToDouble(Person::getIncome)
                        .sum());
        return functionResult;
    }
}
