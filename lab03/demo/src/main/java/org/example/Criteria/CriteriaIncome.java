package org.example.Criteria;

import org.example.model.People;
import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CriteriaIncome implements Criteria{
    @Override
    public List<Person> meetCriteria(SearchParameters param) {
        List<Person> result = People.Data;
        if(param.getIncomeFrom()!=0.0){
            result = result
                    .stream()
                    .filter(person -> person.getIncome()> param.getIncomeFrom())
                    .collect(Collectors.toList());
        }
        if(param.getIncomeTo()!=0.0){
            result = result
                    .stream()
                    .filter(person -> person.getIncome()< param.getIncomeTo())
                    .collect(Collectors.toList());
        }

        return result;
    }
}
