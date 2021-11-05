package org.example.conditions.name;

import org.example.conditions.Conditions;
import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;
import java.util.stream.Collectors;

public class SurnameOfPersonCondition implements Conditions {
    @Override
    public void checkConditions(Results results, SearchParameters searchParameters){
        if(conditionIsValid(searchParameters)){
            filterAndAssignToList(results,searchParameters);
        }
    }
    private void filterAndAssignToList(Results results, SearchParameters searchParameters) {
        results.setItems(results.getItems().stream()
                .filter(person -> formattedString(person.getName()).equals(formattedString(searchParameters.getName())))
                .filter(person -> searchNameEqualsPersonSurname(person,searchParameters))
                .collect(Collectors.toList()));
    }
    private boolean searchNameEqualsPersonSurname(Person person, SearchParameters searchParameters) {
        return formattedString(person.getSurname()).equals(formattedString(searchParameters.getSurname()));
    }
    private Object formattedString(String name) {
        return name.toLowerCase();
    }
    private boolean conditionIsValid(SearchParameters searchParameters) {
        return searchParameters.getSurname() != null;
    }

}
