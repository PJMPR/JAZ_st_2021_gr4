package org.example.criteria.filters;

import org.example.criteria.Criteria;
import org.example.model.Person;
import java.util.List;
import java.util.stream.Collectors;

public class AgeFromCriteria implements Criteria {

    int num;

    public AgeFromCriteria(int number){
        this.num = number;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        if(num > 0) {
            return persons.stream()
                    .filter(person -> person.getAge() >= num)
                    .collect(Collectors.toList());
        }
        return persons;
    }
}