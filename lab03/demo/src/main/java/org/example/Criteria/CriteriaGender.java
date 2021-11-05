package org.example.Criteria;

import org.example.model.People;
import org.example.model.Person;
import org.example.queries.search.SearchParameters;


import java.util.List;
import java.util.stream.Collectors;

public class CriteriaGender implements Criteria {

    public List<Person> meetCriteria(SearchParameters param) {

        return People.Data
                .stream()
                .filter(person -> param.getSelectedGenders().contains(person.getGender()))
                .collect(Collectors.toList());
    }
}