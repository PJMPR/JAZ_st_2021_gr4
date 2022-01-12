package org.example.queries.search.calculations;

import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.FunctionsParameters;

public interface Calculation<TEntity> {
    void setFunctionParameters(FunctionsParameters functionParameters);
    boolean canCalculate();
    FunctionResult calculate(Results<TEntity> results);
}
