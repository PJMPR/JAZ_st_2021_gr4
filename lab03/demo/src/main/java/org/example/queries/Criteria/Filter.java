package org.example.queries.Criteria;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public interface Filter {
    void filtered (Results results, SearchParameters searchParameters);
}
