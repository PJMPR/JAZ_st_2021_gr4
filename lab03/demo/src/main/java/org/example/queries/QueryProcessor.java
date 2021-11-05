package org.example.queries;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public class QueryProcessor {
    public Results GetResults(SearchParameters parameters){

        ResultCreator creator = new ResultCreator();
        creator.setParameters(parameters);

        return creator.prepareResult();
    }



}
