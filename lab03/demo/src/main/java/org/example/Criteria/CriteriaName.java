package org.example.Criteria;
import org.example.model.Gender;
import org.example.model.People;
import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CriteriaName implements Criteria {
    @Override
    public List<Person> meetCriteria(SearchParameters param) {

        return People.Data
                .stream()
                .filter(person -> person.getName().equals(param.getName().toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }
}