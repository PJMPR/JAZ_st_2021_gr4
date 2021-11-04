package org.example.queries.Criteria;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public class CriteriaSurname implements Filter{
    @Override
    public void filtered(Results results, SearchParameters searchParameters) {
        if (searchParameters.getSurname()!=null)
            results.setItems(results.getItems().stream() .filter(person -> (person.getSurname().equalsIgnoreCase(searchParameters.getSurname()))).toList());
    }
}
