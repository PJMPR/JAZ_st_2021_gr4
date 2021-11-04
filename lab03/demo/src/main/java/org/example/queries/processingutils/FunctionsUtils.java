package org.example.queries.processingutils;

import org.example.model.Person;
import org.example.queries.results.FunctionResult;
import org.example.queries.search.Funcs;

import java.util.List;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

import static org.example.queries.search.Funcs.AVARAGE;
import static org.example.queries.search.Funcs.SUM;

public class FunctionsUtils {

    List<Person> people;
    Funcs func;
    String fieldName;
    FunctionResult functionResult;

    public FunctionsUtils(List<Person> people, Funcs func, String fieldName) {
        this.people = people;
        this.func = func;
        this.fieldName = fieldName;
        functionResult = new FunctionResult();
        functionResult.setFieldName(fieldName);
    }

    public FunctionResult ageStats() {
        switch (func){
            case AVARAGE -> average(Person::getAge);
            case SUM -> sum(Person::getAge);
        }
        return functionResult;
    }


    public FunctionResult incomeStats() {
        switch (func){
            case AVARAGE -> average(Person::getIncome);
            case SUM -> sum(Person::getIncome);
        }
        return functionResult;
    }

    public FunctionResult calculateResult(){
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

    private void sum(ToDoubleFunction<? super Person> mapper){
        functionResult.setFunction(SUM);
        functionResult.setValue(
                people.stream().mapToDouble(mapper).sum());
    }

    private void average(ToDoubleFunction<? super Person> mapper){
        functionResult.setFunction(AVARAGE);
        functionResult.setValue(
                people
                .stream()
                .collect(Collectors.averagingDouble((mapper))));
    }



}
