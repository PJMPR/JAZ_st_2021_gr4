package org.example.conditions;

import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

public interface Conditions {
     void checkConditions(Results results, SearchParameters searchParameters);
}
