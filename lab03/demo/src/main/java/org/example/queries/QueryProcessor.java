package org.example.queries;

import org.example.criteria.*;
import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.List;

public class QueryProcessor {

    public Results GetResults(SearchParameters parameters){
        Results result = new Results();

        List<Criteria> criteria = List.of(
                new CriteriaFromAge(),
                new CriteriaGender(),
                new CriteriaIncomeFrom(),
                new CriteriaIncomeTo(),
                new CriteriaName(),
                new CriteriaSurname(),
                new CriteriaToAge()
        );

        criteria.forEach(filter ->
                filter.meetCriteria(result,parameters));

        result.setItems(People.Data);



        return result;




    }
}
