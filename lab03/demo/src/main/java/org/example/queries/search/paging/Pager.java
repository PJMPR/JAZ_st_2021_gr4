package org.example.queries.search.paging;

import org.example.queries.QueryResultsProcessing;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class Pager<TEntity, TSearchParams> implements QueryResultsProcessing<TEntity, TSearchParams>, Paging{
    SearchParameters<TSearchParams> parameters;

    @Override
    public void setParameters(SearchParameters<TSearchParams> parameters) {
        this.parameters=parameters;
    }

    @Override
    public void process(Results<TEntity> results) {
        int skip = (parameters.getPage().getPageNumber()-1)*parameters.getPage().getSize();
        int take = parameters.getPage().getSize();
        int pages = results.getItems().size()/take;
        if(results.getItems().size()%take!=0) pages++;
        results.setItems(results.getItems().stream().skip(skip).limit(take).collect(Collectors.toList()));
        results.setCurrentPage(parameters.getPage().getPageNumber());
        results.setPages(pages);
    }
}
