package org.example.conditions;

import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;

import java.util.stream.Collectors;

public class GenderCondition implements Conditions{
    @Override
    public void checkConditions(Results results, SearchParameters searchParameters) {
        if(conditionIsValid(searchParameters)){
            filterAndAssignToList(results,searchParameters);
        }
    }

    private void filterAndAssignToList(Results results, SearchParameters searchParameters) {
        results.setItems(results.getItems().stream()
                .filter(person -> searchGendersContainPersonGenders(person,searchParameters))
                .collect(Collectors.toList()));
    }
    private boolean conditionIsValid(SearchParameters searchParameters) {
        return searchParameters.getSelectedGenders().size() > 0;
    }
    private boolean searchGendersContainPersonGenders(Person person, SearchParameters searchParameters) {
        return searchParameters.getSelectedGenders().contains(person.getGender());
    }











}
