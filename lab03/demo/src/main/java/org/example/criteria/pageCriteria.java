package org.example.criteria;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class pageCriteria implements criteria {

    @Override
    public void meetCriteria(Results results, SearchParameters searchParams) {
        if(searchParams.getPage() != null) {
            results.setPages(pageNumbers(results, searchParams));
            results.setCurrentPage(searchParams.getPage().getPageNumber());
            results.setItems(results
                    .getItems()
                    .stream()
                    .skip(pagesToSkip(results, searchParams))
                    .limit(searchParams
                            .getPage()
                            .getSize())
                    .collect(Collectors.toList()));
        }
    }

    private int pageNumbers(Results results, SearchParameters searchParams) {
        int result = (results.getItems().size() / searchParams.getPage().getSize() + 1);
        return result;
    }

    private int pagesToSkip(Results results, SearchParameters searchParams) {
        int result = (results.getCurrentPage()-1) * searchParams.getPage().getSize();
        return result;
    }
}