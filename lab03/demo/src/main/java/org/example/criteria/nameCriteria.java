package org.example.criteria;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.Locale;
import java.util.stream.Collectors;

public class nameCriteria implements criteria {

    @Override
    public void meetCriteria(Results results, SearchParameters searchParams) {
        if(searchParams.getName() != null) {
            results.setItems(results
                    .getItems()
                    .stream()
                    .filter(person -> person
                            .getName()
                            .toLowerCase(Locale.ROOT)
                            .equals(searchParams
                                    .getName()
                                    .toLowerCase(Locale.ROOT)))
                    .collect(Collectors.toList()));
        }
    }
}