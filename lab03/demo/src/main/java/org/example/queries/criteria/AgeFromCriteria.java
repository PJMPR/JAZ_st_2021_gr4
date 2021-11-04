package org.example.queries.criteria;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;
import java.util.stream.Collectors;

public class AgeFromCriteria implements Criteria {

    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
        results.setItems(
                results.getItems()
                        .stream()
                        .filter(person -> person
                                .getAge() >= searchParameters
                                .getAgeFrom())
                        .collect(Collectors.toList()));
    }
}
