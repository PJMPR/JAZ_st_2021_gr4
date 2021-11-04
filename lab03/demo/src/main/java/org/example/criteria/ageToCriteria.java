package org.example.criteria;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;
import java.util.stream.Collectors;

public class ageToCriteria implements criteria {

        @Override
        public void meetCriteria(Results results, SearchParameters searchParams) {
            if (searchParams.getAgeTo() > 0) {
                results.setItems
                        (results
                                .getItems()
                                .stream()
                                .filter(person -> person
                                        .getAge() <= searchParams
                                        .getAgeTo())
                                .collect(Collectors.toList()));
            }
        }
    }