package org.example.queries.criteria;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class PageCriteria implements Criteria{

    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
        if(searchParameters.getPage() != null){
            results.setPages(pageNumbers(results,searchParameters));
            results.setCurrentPage(searchParameters.getPage().getPageNumber());
            results.setItems(
                    results.getItems()
                            .stream()
                            .skip(pagesToSkip(results, searchParameters))
                            .limit(searchParameters
                                    .getPage()
                                    .getSize())
                            .collect(Collectors.toList()));
        }
    }
    private int pageNumbers(Results results, SearchParameters searchParameters){
        int result = (results.getItems().size() / searchParameters.getPage().getSize() + 1);
        return result;
    }

    private int pagesToSkip(Results results, SearchParameters searchParameters){
        int result = (results.getCurrentPage()-1) * searchParameters.getPage().getSize();
        return result;
    }
}
