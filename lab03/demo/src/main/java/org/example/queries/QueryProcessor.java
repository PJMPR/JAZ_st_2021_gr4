package org.example.queries;

import org.example.model.People;
import org.example.queries.processingutils.FunctionsUtils;
import org.example.queries.processingutils.PageUtils;
import org.example.queries.processingutils.QueryProcessorUtils;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;


public class QueryProcessor {

    public Results GetResults(SearchParameters parameters) {
        Results result = new Results();

        result.setItems(People.Data);

        QueryProcessorUtils queryProcessorUtils = new QueryProcessorUtils(parameters, result);
        queryProcessorUtils.filterFields();
        queryProcessorUtils.setFunctionResultsAndVars();

        new PageUtils(parameters.getPage(), result).setPageVars();

        return result;
    }



}
