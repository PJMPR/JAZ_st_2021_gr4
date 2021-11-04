package org.example.criteria.filters;

import org.example.criteria.Criteria;
import org.example.model.Person;
import java.util.List;
import java.util.stream.Collectors;

public class IncomeFromCriteria implements Criteria {

    double num;

    public IncomeFromCriteria(double number){
        this.num = number;
    }

    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        if(num > 0) {
            return persons.stream()
                    .filter(person -> person.getIncome() >= num)
                    .collect(Collectors.toList());
        }
        return persons;
    }
}