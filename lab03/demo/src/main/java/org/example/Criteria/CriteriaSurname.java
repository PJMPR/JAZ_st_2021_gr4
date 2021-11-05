package org.example.Criteria;

import org.example.model.People;
import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.stream.Collectors;

public class CriteriaSurname implements Criteria{
    @Override
    public List<Person> meetCriteria(SearchParameters param) {
        return People.Data
                .stream()
                .filter(person -> person.getSurname().equals(param.getSurname()))
                .collect(Collectors.toList());
    }
}
