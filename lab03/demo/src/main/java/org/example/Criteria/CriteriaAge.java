package org.example.Criteria;

import org.example.model.People;
import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.stream.Collectors;

public class CriteriaAge implements Criteria{
    @Override
    public List<Person> meetCriteria(SearchParameters param) {
        List<Person> result = People.Data;
        if(param.getAgeFrom()!=0.0){
            result = result
                    .stream()
                    .filter(person -> person.getIncome()> param.getIncomeFrom())
                    .collect(Collectors.toList());
        }
        if(param.getAgeTo()!=0.0){
            result = result
                    .stream()
                    .filter(person -> person.getIncome()< param.getIncomeTo())
                    .collect(Collectors.toList());
        }

        return result;
    }
}
