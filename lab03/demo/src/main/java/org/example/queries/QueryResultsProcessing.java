package org.example.queries;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public interface QueryResultsProcessing<TEntity, TSearchParams> {
    void setParameters(SearchParameters<TSearchParams> parameters);
    void process(Results<TEntity> results);
}
