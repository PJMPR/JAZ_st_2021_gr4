package org.example.queries.search.calculations;

import org.example.queries.QueryResultsProcessing;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Calculator<TEntity, TSearchParams> implements QueryResultsProcessing<TEntity, TSearchParams>, Calculate {

    SearchParameters<TSearchParams> parameters;
    List<Calculation<TEntity>> calculations = List.of(
            new FieldCalculation<TEntity>(Collectors.averagingDouble(Number::doubleValue), Funcs.AVARAGE),
            new FieldCalculation<TEntity>(Collectors.summingDouble(Number::doubleValue), Funcs.SUM)
    );
    @Override
    public void setParameters(SearchParameters<TSearchParams> parameters) {
        this.parameters=parameters;
    }

    @Override
    public void process(Results<TEntity> results) {
        results.setFunctionResults(
        parameters.getFunctions()
                .stream()
                .map(x->{
                            calculations.forEach(c->c.setFunctionParameters(x));
                            Calculation<TEntity> calculation = calculations.stream().filter(Calculation::canCalculate).findFirst().orElse(null);
                            if(calculation==null)return null;
                            return calculation.calculate(results);
                        })
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
    }

}
