package org.example.queries.criteria.filters;

import org.example.queries.criteria.Criteria;
import org.example.model.Person;
import java.util.List;
import java.util.stream.Collectors;

public class AgeToCriteria implements Criteria {

    int num;

    public AgeToCriteria(int num){this.num = num;}

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        if(num > 0){
            return persons.stream()
                .filter(person -> person.getAge() <= num)
                .collect(Collectors.toList());
        }
        return persons;
    }
}