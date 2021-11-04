package org.example.queries.processingutils;

import org.example.model.Gender;
import org.example.model.Person;
import org.example.queries.criteria.*;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.lang.reflect.Field;
import java.util.*;

public class QueryProcessorUtils {
    ICriterion criterion = null;
    SearchParameters parameters;
    Results result;

    public QueryProcessorUtils(SearchParameters parameters, Results result) {
        this.parameters = parameters;
        this.result = result;
    }
    private Object getValue(Field field, Object object) {
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void filterFields(){
        final Map<String, Object> fieldNamesValues =
                Arrays.stream(parameters.getClass().getDeclaredFields())
                        .collect(
                                HashMap::new,
                                (map, field) -> map.put(field.getName(), getValue(field, parameters)),
                                HashMap::putAll);

        fieldNamesValues.forEach((fieldName, value) -> {
            List<Person> peopleList = filterField(fieldName, value, result.getItems());
            if (peopleList.size() > 0)
                result.setItems(peopleList);
        });
    }

    public List<Person> filterField(String fieldName, Object value, List<Person> personList){
        if(value != null){
            switch (fieldName){
                case "name" -> criterion = new NameCriterion(value.toString());
                case "surname" -> criterion = new SurnameCriterion(value.toString());
                case "ageFrom" -> criterion = new AgeFromCriterion((Integer) value);
                case "ageTo" -> criterion = new AgeToCriterion((Integer) value);
                case "incomeFrom" -> criterion = new IncomeFromCriterion((Double) value);
                case "incomeTo" -> criterion = new IncomeToCriterion((Double) value);
                case "selectedGenders" -> {
                    List<Gender> genderList = (List<Gender>) value;

                    List<Person> multiGenderSearchResult = new ArrayList<>();

                    genderList.forEach(
                            gender -> {
                                GenderCriterion genderCriterion = new GenderCriterion(gender);
                                multiGenderSearchResult.addAll(genderCriterion.meetsCriterion(personList));
                            }
                    );

                    return multiGenderSearchResult;

                }
                default -> criterion = new NameCriterion(null);
            }
        }
        return criterion.meetsCriterion(personList);
    }

    public void setFunctionResultsAndVars(){
        result.setFunctionResults(
                parameters.getFunctions()
                        .stream()
                        .map(function -> {
                            FunctionsUtils functions = new FunctionsUtils(result.getItems().stream().toList(), function.getFunction(), function.getFieldName());
                            return functions.calculateResult();
                        }).toList());
    }
}
