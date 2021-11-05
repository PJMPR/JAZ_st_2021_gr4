package org.example.queries;

import org.example.conditions.Conditions;
import org.example.functions.FunctionResultLogic;
import org.example.model.ConditionsOrganiser;
import org.example.model.People;
import org.example.queries.results.Results;
import org.example.queries.search.SearchParameters;
import java.util.List;

public class ResultCreator {
    private SearchParameters parameters;
    private final FunctionResultLogic function;
    private final ConditionsOrganiser listOfConditionsObject;
    private final Results result;

    public ResultCreator() {
        this.function = new FunctionResultLogic();
        this.listOfConditionsObject =  new ConditionsOrganiser();
        this.result =  new Results();
    }

    public void setParameters(SearchParameters parameters) {
        this.parameters = parameters;
    }

    public Results prepareResult() {
        List<Conditions>conditions = listOfConditionsObject.getListOfConditions();
        result.setItems(People.Data);
        conditions.forEach(filter -> filter.checkConditions(result,parameters));
        function.checkWhichFunctionNeeded(parameters, result);
        return result;
    }
}
