package org.example.conditions.income;

import org.example.conditions.Conditions;
import org.example.model.Person;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;
import java.util.stream.Collectors;

public class IncomeToCondition implements Conditions {
    @Override
    public void checkConditions(Results results, SearchParameters searchParameters) {
        if(conditionIsValid(searchParameters)){
            filterAndAssignToList(results,searchParameters);
        }
    }
    private void filterAndAssignToList(Results results, SearchParameters searchParameters) {
        results.setItems(results.getItems().stream()
                .filter(person -> incomeSmallerThanCondition(person,searchParameters))
                .collect(Collectors.toList()));
    }
    private boolean conditionIsValid(SearchParameters searchParameters) {
        return searchParameters.getIncomeTo() > 0;
    }
    private boolean incomeSmallerThanCondition(Person person, SearchParameters searchParameters) {
        return person.getIncome() <= searchParameters.getIncomeTo();
    }
}
