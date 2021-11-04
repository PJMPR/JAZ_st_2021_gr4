package org.example.queries;

import org.example.model.Gender;
import org.example.model.People;
import org.example.model.Person;
import org.example.queries.Criteria.*;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QueryProcessor {

        private List<Filter> listCriteria = List.of(
                new CriteriaPage(),
                new CriteriaGender(),
                new CriteriaAgeFrom(),
                new CriteriaAgeTo(),
                new CriteriaIncomeFrom(),
                new CriteriaIncomeTo(),
                new CriteriaName(),
                new CriteriaSurname()

        );

        public Results GetResults(SearchParameters parameters){
            Results results = new Results();
            results.setItems(People.Data);

            listCriteria.forEach(listCriteria -> listCriteria.filtered(results,parameters));

            return results;
        }
}

