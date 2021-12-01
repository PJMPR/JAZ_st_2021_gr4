package org.example.queries.search.filters;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;

@FunctionalInterface
public interface FilterPredicate<TEntity, TSearchParams> {
    boolean check(TEntity p, SearchParameters<TSearchParams> params);
}
