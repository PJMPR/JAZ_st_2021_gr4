package org.example.queries.search.filters;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.function.Predicate;

public class SimpleCriteria<TEntity,TSearchParams> extends FilterBase<TEntity, TSearchParams> implements Filter{

    Predicate<SearchParameters<TSearchParams>> checkParams;
    FilterPredicate<TEntity, TSearchParams> checkPerson;

    public SimpleCriteria(Predicate<SearchParameters<TSearchParams>> checkParams, FilterPredicate<TEntity,TSearchParams> checkPerson){
        this.checkParams=checkParams;
        this.checkPerson = checkPerson;
    }

    @Override
    protected boolean canFilter() {
        return checkParams.test(parameters);
    }

    @Override
    protected boolean check(TEntity person) {
        return checkPerson.check(person, parameters);
    }
}
