package org.example.queries;

import org.example.queries.criteria.*;
import org.example.queries.results.AgeResult;
import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;

public class QueryProcessor {

    List<Criteria> criteria = List.of(
            new AgeFromCriteria(),
            new AgeToCriteria(),
            new GenderCriteria(),
            new IncomeToCriteria(),
            new IncomeFromCriteria(),
            new NameCriteria(),
            new SurnameCriteria(),
            new PageCriteria()
    );

    public Results GetResults(SearchParameters parameters) {
        Results result = new Results();
        AgeResult function = new AgeResult();
        result.setItems(People.Data);
        criteria.forEach(filter -> filter.meetCriteria(result,parameters));
        function.ageIncome(parameters, result);
        return result;
    }
}