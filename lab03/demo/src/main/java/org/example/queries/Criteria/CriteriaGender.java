package org.example.queries.Criteria;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public class CriteriaGender implements Filter{

    @Override
    public void filtered(Results results, SearchParameters searchParameters) {
        if (searchParameters.getSelectedGenders().size() > 0)
            results.setItems(results.getItems().stream() .filter(person -> (searchParameters.getSelectedGenders().contains(person.getGender()))).toList());

    }
}
