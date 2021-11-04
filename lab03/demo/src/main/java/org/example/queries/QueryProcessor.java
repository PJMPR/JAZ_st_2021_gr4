package org.example.queries;

import org.example.criteria.Criteria;
import org.example.criteria.AgeFromCriteria;
import org.example.criteria.AgeToCriteria;
import org.example.criteria.GenderCriteria;
import org.example.criteria.IncomeToCriteria;
import org.example.criteria.IncomeFromCriteria;
import org.example.criteria.NameCriteria;
import org.example.criteria.SurnameCriteria;
import org.example.criteria.PageCriteria;
import org.example.functions.AgeGetResult;
import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;

public class QueryProcessor {

    List<Criteria> criteris = List.of(
            new AgeFromCriteria(),
            new AgeToCriteria(),
            new GenderCriteria(),
            new IncomeToCriteria(),
            new IncomeFromCriteria(),
            new NameCriteria(),
            new SurnameCriteria(),
            new PageCriteria()
    );

    public Results GetResults(SearchParameters parameters){
        Results result = new Results();
        AgeGetResult function = new AgeGetResult();
        result.setItems(People.Data);
        criteris.forEach(filter -> filter.meetCriteria(result,parameters));
        function.ageIncome(parameters, result);
        return result;
    }
}