package org.example.criteria;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.Locale;
import java.util.stream.Collectors;

public class surnameCriteria implements criteria {

    @Override
    public void meetCriteria(Results results, SearchParameters searchParams) {
        if(searchParams.getSurname() != null) {
            results.setItems(results
                    .getItems()
                    .stream()
                    .filter(person -> person
                            .getSurname()
                            .toLowerCase(Locale.ROOT)
                            .equals(searchParams
                                    .getSurname()
                                    .toLowerCase(Locale.ROOT)))
                    .collect(Collectors.toList()));
        }
    }
}