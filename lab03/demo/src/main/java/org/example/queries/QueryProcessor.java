package org.example.queries;

import org.example.model.Gender;
import org.example.model.People;
import org.example.model.Person;
import org.example.queries.criteria.*;
import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.SearchParameters;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class QueryProcessor {

    public Results GetResults(SearchParameters parameters) {
        Results result = new Results();

        result.setItems(People.Data);

        ICriterion criterion;

        List<Field> criteriaFields =
                Arrays.stream(parameters.getClass().getDeclaredFields())
                        .toList()
                        .stream()
                        .filter(field -> {
                            try {
                                field.setAccessible(true);
                                return field.get(parameters) != null;
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                            return true;
                        })
                        .toList();

        for (Field field : criteriaFields) {
            try {
                field.setAccessible(true);
                Object value = field.get(parameters);

                if (value != null) {
                    switch (field.getName()){
                        case "name": {
                            criterion = new NameCriterion(value.toString());
                            result.setItems(criterion.meetsCriterion(result.getItems()));
                        }
                        case "surname": {
                            criterion = new NameCriterion(value.toString());
                            result.setItems(criterion.meetsCriterion(result.getItems()));
                        }

                    }
                    if (field.getName().equals("ageFrom")) {
                        criterion = new AgeFromCriterion((Integer) value);
                        result.setItems(criterion.meetsCriterion(result.getItems()));
                    }
                    if (field.getName().equals("ageTo") && (Integer) value > 0) {
                        criterion = new AgeToCriterion((Integer) value);
                        result.setItems(criterion.meetsCriterion(result.getItems()));
                    }
                    if (field.getName().equals("incomeFrom")) {
                        criterion = new IncomeFromCriterion((Double) value);
                        result.setItems(criterion.meetsCriterion(result.getItems()));
                    }
                    if (field.getName().equals("incomeTo") && (Double) value > 0) {
                        criterion = new IncomeToCriterion((Double) value);
                        result.setItems(criterion.meetsCriterion(result.getItems()));
                    }

                    if (field.getName().equals("selectedGenders") && !Objects.equals(value.toString(), "[]")) {
                        List<Gender> genderList = (List<Gender>) value;
                        List<ICriterion> genderCriteria = new ArrayList<>();

                        genderList.forEach(gender -> genderCriteria.add(new GenderCriterion(gender)));

                        List<Person> multiGenderSearchResult = new ArrayList<>();

                        genderCriteria.forEach(
                                genderCriterion ->
                                        multiGenderSearchResult.addAll(genderCriterion.meetsCriterion(result.getItems())));

                        result.setItems(multiGenderSearchResult);
                    }

                }


            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        if(parameters.getPage() != null) {
            result.setCurrentPage(parameters.getPage().getPageNumber());
            result.setPages(max(result.getItems().size() / parameters.getPage().getSize(), 1));

            int noOfRecordsToSkip = parameters.getPage().getSize() * (result.getCurrentPage() - 1);

            result.setItems(result.getItems()
                    .stream()
                    .skip(noOfRecordsToSkip > result.getItems().size() ? 0 : noOfRecordsToSkip)
                    .limit(parameters.getPage().getSize())
                    .toList());
        }

        List<FunctionResult> functionResults = new ArrayList<>();
        for (FunctionsParameters function : parameters.getFunctions()) {
            FunctionResult functionResult = new FunctionResult();

            functionResult.setFunction(function.getFunction());
            functionResult.setFieldName(function.getFieldName());
            functionResult.setValue(0);

            functionResults.add(functionResult);
        }

        result.setFunctionResults(functionResults);

        return result;
    }


}
