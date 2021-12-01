package org.example.queries;

import org.example.model.People;
import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.PersonSearchParams;
import org.example.queries.search.SearchParameters;
import org.example.queries.search.calculations.Calculate;
import org.example.queries.search.calculations.Calculator;
import org.example.queries.search.filters.Filter;
import org.example.queries.search.filters.SimpleCriteria;
import org.example.queries.search.paging.Pager;

import java.util.List;

public class QueryProcessor<TEntity, TSearchParams> {

    List<QueryResultsProcessing<TEntity, TSearchParams>> processors;
    Results<TEntity> result;
    /*= List.of(
            new SimpleCriteria<>(p -> p.getParams().getAgeFrom() > 0,
                    (person, params) -> person.getAge() > params.getParams().getAgeFrom()),
            new Pager<>(),
            new Calculator<>()
    );*/

    public Results<TEntity> GetResults(SearchParameters<TSearchParams> parameters){

        processors.forEach(x->x.setParameters(parameters));
        processors.stream().filter(c->c instanceof Filter).forEach(criteria->criteria.process(result));
        processors.stream().filter(c->c instanceof Pager).forEach(pager->pager.process(result));
        processors.stream().filter(f->f instanceof Calculate).forEach(calculator->calculator.process(result));
        return result;
    }
}
