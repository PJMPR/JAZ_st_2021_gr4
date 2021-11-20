package org.example.Solutions;

import org.example.Exception.ExceptionModel;
import org.example.Runner;

import java.io.FileNotFoundException;

public class FileNotFoundSolution extends ExceptionModel {
    public FileNotFoundSolution() {
        super(FileNotFoundSolution.class);
    }
    @Override
    public void useSolution(Runner method, Exception exception) {
        if ( retry(method, 5, 1000) ) {
            defaultResponse(exception);
        }
    }
    @Override
    public boolean ifSolutionFound(Exception error) {
        return error instanceof FileNotFoundException;
    }
    @Override
    public String feedback() {
        return "Error. File not found.";
    }
}