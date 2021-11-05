package org.example.conditions;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class PageCondition implements Conditions {
    @Override
    public void checkConditions(Results results, SearchParameters searchParameters) {
        if(conditionIsValid(searchParameters)){
            filterAndAssignToList(results,searchParameters);
        }
    }

    private void filterAndAssignToList(Results results, SearchParameters searchParameters) {
        results.setPages(findPageNumber(results,searchParameters));
        results.setCurrentPage(searchParameters.getPage().getPageNumber());
        results.setItems(results.getItems().stream()
                .skip(pagesToSkip(results, searchParameters))
                .limit(searchParameters.getPage().getSize())
                .collect(Collectors.toList()));
    }
    private int pagesToSkip(Results results, SearchParameters searchParameters) {
        return (results.getCurrentPage()-1) * searchParameters.getPage().getSize();
    }
    private int findPageNumber(Results results, SearchParameters searchParameters){
        return (results.getItems().size() / searchParameters.getPage().getSize() + 1);
    }
    private boolean conditionIsValid(SearchParameters searchParameters) {
        return searchParameters.getPage() != null;
    }
}
