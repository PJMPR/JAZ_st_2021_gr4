package org.example.queries.criteria;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;
import java.util.Locale;
import java.util.stream.Collectors;

public class CriteriaName implements Criteria {
    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
        if (!(searchParameters.getName() == null)) {
            results.setItems(results.getItems()
                    .stream()
                    .filter(person -> person
                            .getName().toUpperCase(Locale.ROOT)
                            .equals(searchParameters.getName().toUpperCase(Locale.ROOT)))
                    .collect(Collectors.toList()));
        }
    }
}
