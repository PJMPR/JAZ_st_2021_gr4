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
                        (map,field)->map.put(field.getName(), getValue(field, parameters)),
                        HashMap::putAll);

        QueryProcessorUtils queryProcessorUtils = new QueryProcessorUtils();

        fieldNamesValues.forEach((fieldName, value) -> {
            if (queryProcessorUtils.filterField(fieldName, value, result.getItems()).size() > 0)
                result.setItems(queryProcessorUtils.filterField(fieldName, value, result.getItems()));
        });

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

        parameters.getFunctions().forEach(
                function -> {
                    FunctionsUtils functions = new FunctionsUtils(result.getItems().stream().toList(), function.getFunction(), function.getFieldName());
                    functionResults.add(functions.calculate());
        });

        result.setFunctionResults(functionResults);

        return result;
    }

    private Object getValue(Field field, Object object)  {
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


}
