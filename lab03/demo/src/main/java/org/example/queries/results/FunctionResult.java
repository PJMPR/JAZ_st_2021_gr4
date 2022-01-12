package org.example.queries.results;

import org.example.queries.search.Funcs;

public class FunctionResult {

    private String fieldName;
    private Funcs function;
    private double result;

    public FunctionResult() {
    }

    public FunctionResult(String fieldName, Funcs function, double result) {
        this.fieldName = fieldName;
        this.function = function;
        this.result = result;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Funcs getFunction() {
        return function;
    }

    public void setFunction(Funcs function) {
        this.function = function;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
