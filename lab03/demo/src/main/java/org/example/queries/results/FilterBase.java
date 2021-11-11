package org.example.queries.results;

import org.example.model.Person;
import org.example.queries.search.SearchParameters;
import org.example.queries.search.filters.Filter;

import java.util.stream.Collectors;

public abstract class FilterBase implements Filter {

    protected SearchParameters parameters;

    @Override
    public void setParameters(SearchParameters parameters) {
        this.parameters=parameters;
    }

    @Override
    public void filter(Results results) {
        if(checkParameters())
            results.setItems(results
                    .getItems()
                    .stream()
                    .filter(person->checkPerson(person))
                    .collect(Collectors.toList()));
    }

    protected abstract boolean checkPerson(Person person) ;
    protected abstract boolean checkParameters();
}
