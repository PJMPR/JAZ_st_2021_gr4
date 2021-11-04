package org.example.queries;

import org.example.criteria.criteria;
import org.example.criteria.ageFromCriteria;
import org.example.criteria.ageToCriteria;
import org.example.criteria.genderCriteria;
import org.example.criteria.incomeToCriteria;
import org.example.criteria.incomeFromCriteria;
import org.example.criteria.nameCriteria;
import org.example.criteria.surnameCriteria;
import org.example.criteria.pageCriteria;
import org.example.functions.ageGetResult;
import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;

public class QueryProcessor {

    List<criteria> allCriteria = List.of(
            new ageFromCriteria(),
            new ageToCriteria(),
            new genderCriteria(),
            new incomeToCriteria(),
            new incomeFromCriteria(),
            new nameCriteria(),
            new surnameCriteria(),
            new pageCriteria()
    );

    public Results GetResults(SearchParameters params){
        Results result = new Results();
        ageGetResult function = new ageGetResult();

        result.setItems(People.Data);
        allCriteria.forEach(filter -> filter.meetCriteria(result, params));
        function.ageIncome(params, result);

        return result;
    }
}