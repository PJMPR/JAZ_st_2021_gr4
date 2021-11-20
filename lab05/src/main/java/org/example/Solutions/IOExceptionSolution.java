package org.example.Solutions;

import org.example.Exception.ExceptionModel;
import org.example.Runner;

import java.io.IOException;

public class IOExceptionSolution extends ExceptionModel {
    public IOExceptionSolution() {
        super(IOExceptionSolution.class);
    }

    @Override
    public void useSolution(Runner method, Exception exception) {
        if(retry(method, 10, 1000)){
            defaultResponse(exception);
        }
    }

    @Override
    public boolean ifSolutionFound(Exception error) {
        return error instanceof IOException;
    }

    @Override
    public String feedback() {
        return "Resource error";
    }
}