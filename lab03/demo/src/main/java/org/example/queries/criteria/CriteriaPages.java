package org.example.queries.criteria;

import org.example.queries.results.Results;
import org.example.queries.search.PageFunctions;
import org.example.queries.search.SearchParameters;
import java.util.stream.Collectors;

public class CriteriaPages implements Criteria{
    @Override
    public void meetCriteria(Results results, SearchParameters searchParameters) {
        if (searchParameters.getPage() != null){
            results.setCurrentPage(searchParameters.getPage().getPageNumber());
            results.setPages(PageFunctions.pageNumber(results,searchParameters));
            results.setItems(results.getItems()
                    .stream()
                    .skip(PageFunctions.skipPages(results, searchParameters))
                    .limit(searchParameters
                            .getPage()
                            .getSize())
                    .collect(Collectors.toList()));
        }
    }
}
