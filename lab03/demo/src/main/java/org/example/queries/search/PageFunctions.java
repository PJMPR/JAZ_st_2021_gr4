package org.example.queries.search;

import org.example.queries.results.Results;

public class PageFunctions {
    public static int pageNumber(Results results, SearchParameters searchParameters){
        return results.getItems().size() / (searchParameters.getPage().getSize()) + 1;
    }

    public static int skipPages(Results results, SearchParameters searchParameters){
        return (results.getCurrentPage()-1) * searchParameters.getPage().getSize();
    }
}
