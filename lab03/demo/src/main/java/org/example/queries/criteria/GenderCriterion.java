package org.example.queries.criteria;

import org.example.model.Gender;
import org.example.model.Person;

import java.util.List;

public record GenderCriterion(Gender wantedGender) implements ICriterion {

    @Override
    public List<Person> meetsCriterion(List<Person> persons) {
        return persons.stream()
                .filter(person -> person.getGender().equals(wantedGender))
                .toList();
    }
}
