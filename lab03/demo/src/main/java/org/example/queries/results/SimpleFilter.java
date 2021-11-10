package org.example.queries.results;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.function.Predicate;

public class SimpleFilter extends FilterBase{

    Predicate<SearchParameters> checkParams;
    FilterPredicate checkPersonWithParams;

    public SimpleFilter(Predicate<SearchParameters> checkParams, FilterPredicate checkPersonWithParams) {
        this.checkParams = checkParams;
        this.checkPersonWithParams = checkPersonWithParams;
    }

    @Override
    protected boolean checkPerson(Person person) {
        return checkPersonWithParams.checkPerson(person, parameters);
    }

    @Override
    protected boolean checkParameters() {
        return checkParams.test(parameters);
    }
}
