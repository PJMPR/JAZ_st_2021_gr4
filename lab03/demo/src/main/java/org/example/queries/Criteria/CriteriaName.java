package org.example.queries.Criteria;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public class CriteriaName implements Filter{
    @Override
    public void filtered(Results results, SearchParameters searchParameters) {
        if (searchParameters.getName()!=null)
            results.setItems(results.getItems().stream() .filter(person -> (person.getName().equalsIgnoreCase(searchParameters.getName()))).toList());
    }
}
