package org.example.queries.search.calculations;

import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.Funcs;
import org.example.queries.search.FunctionsParameters;

import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FieldCalculation<TEntity> implements Calculation<TEntity>{

    Collector<Number, ?, Double> collector;
    Funcs funcType;
    FunctionsParameters functionsParameters;
    public FieldCalculation(Collector<Number, ?, Double> collector, Funcs funcType) {
        this.collector = collector;
        this.funcType = funcType;
    }

    @Override
    public void setFunctionParameters(FunctionsParameters functionParameters) {
        this.functionsParameters = functionParameters;
    }

    @Override
    public boolean canCalculate() {
        return functionsParameters.getFunction().equals(funcType);
    }

    @Override
    public FunctionResult calculate(Results<TEntity> results) {
       double result = results.getItems().stream().map(p-> {
            try {
                return (Number)p.getClass().getDeclaredField(functionsParameters.getFieldName()).get(p);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }).filter(Objects::nonNull)
                .collect(collector);
        return new FunctionResult(functionsParameters.getFieldName(), functionsParameters.getFunction(), result);
    }
}
