package org.example.queries;

import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public class QueryProcessor {
    public Results GetResults(SearchParameters parameters){
        Results result = new Results();
        result.setItems(People.Data);

        Filters filters = new Filters(parameters);
        filters.filterData(result);

        Pages pageProcessor = new Pages(parameters.getPage());
        pageProcessor.prepareDataToDisplay(result);

        Functions functionProcessor = new Functions(parameters.getFunctions());
        functionProcessor.calculate(result);

        return result;
    }
}