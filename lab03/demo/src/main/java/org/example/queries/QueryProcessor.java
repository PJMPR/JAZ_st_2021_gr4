package org.example.queries;

import org.example.model.People;
import org.example.queries.criteria.*;
import org.example.queries.results.Results;
import org.example.queries.search.AgeIncome;
import org.example.queries.search.SearchParameters;
import java.util.List;

public class QueryProcessor {

    public Results GetResults(SearchParameters parameters){
        Results result = new Results();

        AgeIncome function = new AgeIncome();
        result.setItems(People.Data);

        criteriaList.forEach(filter -> filter.meetCriteria(result,parameters));
        function.ageIncome(parameters, result);
        return result;
    }

    List<Criteria> criteriaList = List.of(new CriteriaName(), new CriteriaSurname(), new CriteriaAgeFrom(), new CriteriaAgeTo(), new CriteriaIncomeFrom(), new CriteriaIncomeTo(), new CriteriaGender(), new CriteriaPages());
}
