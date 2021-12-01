package org.example.queries.search.filters;

import org.example.model.Person;
import org.example.queries.QueryResultsProcessing;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public abstract class FilterBase<TEntity, TSearchParam> implements QueryResultsProcessing<TEntity, TSearchParam> {

    SearchParameters<TSearchParam> parameters;
    public void setParameters(SearchParameters<TSearchParam> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void process(Results<TEntity> results) {
        if(canFilter())
        results.setItems(results
                .getItems()
                .stream()
                .filter(this::check)
                .collect(Collectors.toList()));
    }

    protected abstract boolean canFilter() ;
    protected abstract boolean check(TEntity person);
}
