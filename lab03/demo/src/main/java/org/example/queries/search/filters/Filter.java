package org.example.queries.search.filters;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public interface Filter {
    void setParameters(SearchParameters parameters);
    void filter(Results results);
}
