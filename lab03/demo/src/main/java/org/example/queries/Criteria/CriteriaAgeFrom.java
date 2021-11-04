package org.example.queries.Criteria;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public class CriteriaAgeFrom implements Filter{

    @Override
    public void filtered(Results results, SearchParameters searchParameters) {
        if(searchParameters.getIncomeFrom()>0)
            results.setItems(results.getItems().stream() .filter(person -> (person.getAge()>=searchParameters.getAgeFrom())).toList());
    }
}