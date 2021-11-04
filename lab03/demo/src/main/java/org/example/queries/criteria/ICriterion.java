package org.example.queries.criteria;

import org.example.model.Person;

import java.util.List;

public interface ICriterion {
    List<Person> meetsCriterion(List<Person> persons);
}
