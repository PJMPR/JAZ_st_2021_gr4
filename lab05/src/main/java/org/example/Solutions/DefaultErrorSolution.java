package org.example.Solutions;

import org.example.Exception.ExceptionModel;
import org.example.Runner;

public class DefaultErrorSolution extends ExceptionModel {
    public DefaultErrorSolution() {
        super(DefaultErrorSolution.class);
    }

    @Override
    public void useSolution(Runner method, Exception exception){
        defaultResponse(exception);
    };

    @Override
    public boolean ifSolutionFound(Exception error) {
        return error != null;
    }

    @Override
    public String feedback() {
        return "Default error";
    }
}