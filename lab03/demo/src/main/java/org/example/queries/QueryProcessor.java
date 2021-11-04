package org.example.queries;

import org.example.model.People;
import org.example.queries.functions.FunctionsUtils;
import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.lang.reflect.Field;
import java.util.*;

import static java.lang.Math.max;

public class QueryProcessor {

    public Results GetResults(SearchParameters parameters) {
        Results result = new Results();

        result.setItems(People.Data);

        final Map<String, Object> fieldNamesValues =
                Arrays.stream(parameters.getClass().getDeclaredFields())
                        .collect(
                            HashMap::new,
                            (map, field) -> map.put(field.getName(), getValue(field, parameters)),
                            HashMap::putAll);

        QueryProcessorUtils queryProcessorUtils = new QueryProcessorUtils();

        fieldNamesValues.forEach((fieldName, value) -> {
            if (queryProcessorUtils.filterField(fieldName, value, result.getItems()).size() > 0)
                result.setItems(queryProcessorUtils.filterField(fieldName, value, result.getItems()));
        });

        PageUtils pageUtils = new PageUtils(parameters.getPage(), result);
        pageUtils.pageOutput();

        result.setFunctionResults(parameters.getFunctions()
                .stream()
                .map(function -> {
                    FunctionsUtils functions = new FunctionsUtils(result.getItems().stream().toList(), function.getFunction(), function.getFieldName());
                    return functions.calculate();
                }).toList());

        return result;
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


}
