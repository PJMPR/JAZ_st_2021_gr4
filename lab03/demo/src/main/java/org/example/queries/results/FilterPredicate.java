package org.example.queries.results;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;

@FunctionalInterface
public interface FilterPredicate {
    boolean checkPerson(Person person, SearchParameters parameters);
}
