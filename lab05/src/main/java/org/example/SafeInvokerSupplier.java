package org.example;

import org.example.Exception.ExceptionModel;
import org.example.Solutions.DefaultErrorSolution;
import org.example.Solutions.FileNotFoundSolution;
import org.example.Solutions.IOExceptionSolution;
import org.example.Solutions.SQLExceptionSolution;

import java.util.List;

public class SafeInvokerSupplier {
     private List<ExceptionModel> listOfSolutions;

    public SafeInvokerSupplier() {
        listOfSolutions=createListOfSolutions();
    }

    private List<ExceptionModel> createListOfSolutions() {
        return List.of(
                new FileNotFoundSolution(),
                new IOExceptionSolution(),
                new SQLExceptionSolution()
        );
    }
    private ExceptionModel listOfHandlers(Exception exception) {
        return listOfSolutions.stream()
                .filter(errorHandler -> errorHandler.ifSolutionFound(exception))
                .findAny().orElse(new DefaultErrorSolution());
    }

    public List<ExceptionModel> getListOfSolutions() {
        return listOfSolutions;
    }
}
