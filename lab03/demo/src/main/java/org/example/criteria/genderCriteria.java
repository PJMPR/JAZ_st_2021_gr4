package org.example.criteria;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class genderCriteria implements criteria {

    @Override
    public void meetCriteria(Results results, SearchParameters searchParams) {
        if(searchParams.getSelectedGenders().size() > 0) {
            results.setItems(results.getItems()
                    .stream().filter(person -> searchParams
                            .getSelectedGenders()
                            .contains(person.getGender()))
                    .collect(Collectors.toList()));
        }
    }
}