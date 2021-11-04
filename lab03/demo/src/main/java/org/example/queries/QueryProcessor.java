package org.example.queries;

import org.example.model.People;
import org.example.queries.results.CountFunc;
import org.example.queries.results.Results;
import org.example.queries.search.ApplyAllFilters;
import org.example.queries.search.Page;
import org.example.queries.search.SearchParameters;

public class QueryProcessor {

    public Results GetResults(SearchParameters parameters){
        Results result = new Results();
        result.setItems(People.Data);
        CountFunc countfunc = new CountFunc();
        Results results = new ApplyAllFilters().apply(result,parameters);
        return countfunc.countAllRequested(results,parameters);
    }
}
