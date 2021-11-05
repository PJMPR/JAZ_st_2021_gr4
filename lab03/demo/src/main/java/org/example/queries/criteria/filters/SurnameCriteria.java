package org.example.queries.criteria.filters;

import org.example.queries.criteria.Criteria;
import org.example.model.Person;
import java.util.List;
import java.util.stream.Collectors;

public class SurnameCriteria implements Criteria {

    String str;

    public SurnameCriteria(String string){this.str = string;}

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        if(str != null){
            return persons.stream()
                    .filter(person -> person.getSurname().equalsIgnoreCase(str))
                    .collect(Collectors.toList());
        }
        return persons;
    }
}