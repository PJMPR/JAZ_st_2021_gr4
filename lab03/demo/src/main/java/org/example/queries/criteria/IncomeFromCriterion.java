package org.example.queries.criteria;

import org.example.model.Person;

import java.util.List;

public record IncomeFromCriterion(double incomeFrom) implements ICriterion {

    @Override
    public List<Person> meetsCriterion(List<Person> persons) {
        return persons.stream()
                .filter(person -> person.getIncome() >= incomeFrom)
                .toList();
    }
}
