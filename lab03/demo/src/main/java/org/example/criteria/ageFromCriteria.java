package org.example.criteria;

import org.example.queries.search.SearchParameters;
import org.example.queries.results.Results;
import java.util.stream.Collectors;

public class ageFromCriteria implements criteria {

    @Override
    public void meetCriteria(Results results, SearchParameters searchParams) {
        results.setItems(results
                .getItems()
                .stream()
                .filter(person -> person
                        .getAge() >= searchParams
                        .getAgeFrom())
                .collect(Collectors.toList()));
    }
}
