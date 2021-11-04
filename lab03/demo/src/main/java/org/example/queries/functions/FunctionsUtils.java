package org.example.queries.functions;

import org.example.model.Person;
import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.queries.search.Funcs.AVARAGE;
import static org.example.queries.search.Funcs.SUM;

public class FunctionsUtils {

    List<Person> people;
    Funcs func;
    String fieldName;

    public FunctionsUtils(List<Person> people, Funcs func, String fieldName) {
        this.people = people;
        this.func = func;
        this.fieldName = fieldName;
    }

    public FunctionResult ageStats() {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFieldName(fieldName);
        switch (func){
            case AVARAGE -> {
                functionResult.setFunction(AVARAGE);
                functionResult.setValue(
                    people
                    .stream()
                    .collect(Collectors.averagingInt(Person::getAge)));
            }
            case SUM -> {
                functionResult.setFunction(SUM);
                functionResult.setValue(
                        people.stream().mapToDouble(Person::getAge).sum());
            }
        }
        return functionResult;
    }


    public FunctionResult incomeStats() {
        FunctionResult functionResult = new FunctionResult();
        functionResult.setFieldName(fieldName);
        switch (func){
            case AVARAGE -> {
                functionResult.setFunction(AVARAGE);
                functionResult.setValue(
                        people
                        .stream()
                        .collect(Collectors.averagingDouble((Person::getIncome))));
            }
            case SUM -> {
                functionResult.setFunction(SUM);
                functionResult.setValue(
                        people.stream().mapToDouble(Person::getIncome).sum());
            }
        }
        return functionResult;
    }

    public FunctionResult calculate(){
        switch (fieldName){
            case "age" -> {
                return ageStats();
            }
            case "income" -> {
                return incomeStats();
            }

        }
        return null;
    }



}
