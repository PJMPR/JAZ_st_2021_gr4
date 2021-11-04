package org.example.queries.Criteria;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public class CriteriaIncomeTo implements Filter{

    @Override
    public void filtered(Results results, SearchParameters searchParameters) {
        if (searchParameters.getIncomeTo()>0)
            results.setItems(results.getItems().stream() .filter(person -> (person.getIncome()<=searchParameters.getIncomeTo())).toList());

    }
}
