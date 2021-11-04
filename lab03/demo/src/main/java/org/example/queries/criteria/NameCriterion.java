package org.example.queries.criteria;

import org.example.model.Person;

import java.util.List;
import java.util.Locale;

public record NameCriterion(String name) implements ICriterion {

    @Override
    public List<Person> meetsCriterion(List<Person> persons) {
        return persons.stream()
                .filter(person -> person.getName().equalsIgnoreCase(name))
                .toList();
    }


}
