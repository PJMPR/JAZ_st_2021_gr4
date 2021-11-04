package org.example.criteria;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public interface criteria {
    void meetCriteria(Results results, SearchParameters searchParams);
}