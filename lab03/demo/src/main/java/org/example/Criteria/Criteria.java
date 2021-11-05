package org.example.Criteria;

import org.example.model.People;
import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.lang.reflect.Parameter;
import java.util.List;

public interface Criteria {
    public List<Person> meetCriteria(SearchParameters param);
}

