package org.example.queries.Criteria;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public class CriteriaIncomeFrom implements Filter{

    @Override
    public void filtered(Results results, SearchParameters searchParameters) {
            results.setItems(results.getItems().stream() .filter(person -> (person.getIncome()>=searchParameters.getIncomeFrom())).toList());
    }
}