package org.example.queries.search;

import org.example.model.Person;
import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import java.util.stream.Collectors;

public class Functions {
    public static FunctionResult ageSum(Results results) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFieldName("age");
        functionResult.setFunction(Funcs.AVARAGE);
        functionResult.setValue(results.getItems()
                .stream()
                .mapToInt(Person::getAge)
                .sum());
        return functionResult;
    }

    public static FunctionResult ageAvarage(Results results) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFieldName("age");
        functionResult.setFunction(Funcs.AVARAGE);
        functionResult.setValue(results.getItems()
                .stream()
                .collect(Collectors.averagingInt(Person::getAge)));
        return functionResult;
    }

    public static FunctionResult incomeSum(Results results) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFieldName("income");
        functionResult.setFunction(Funcs.AVARAGE);
        functionResult.setValue(results.getItems()
                .stream()
                .mapToDouble(Person::getIncome)
                .sum());
        return functionResult;
    }

    public static FunctionResult incomeAvarage(Results results) {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFieldName("income");
        functionResult.setFunction(Funcs.AVARAGE);
        functionResult.setValue(results.getItems()
                .stream()
                .collect(Collectors.averagingDouble(Person::getIncome)));
        return functionResult;
    }
}
