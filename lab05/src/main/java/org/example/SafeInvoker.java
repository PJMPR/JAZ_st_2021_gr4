package org.example;


import org.example.Exception.ExceptionModel;
import org.example.Solutions.*;

import java.util.List;

public class SafeInvoker {
    SafeInvokerSupplier supplier = new SafeInvokerSupplier();

    public ExceptionModel findErrorHandler(Exception exception){
        return listOfHandlers(exception);
    }
    public void invoke(Runner runner){
        try{
            runner.execute();
        }catch (Exception exception){
            findErrorHandler(exception).useSolution(runner, exception);
        }
    }
    private ExceptionModel listOfHandlers(Exception exception) {
        List<ExceptionModel> listOfSolutions = supplier.getListOfSolutions();
        return listOfSolutions.stream()
                .filter(errorHandler -> errorHandler.ifSolutionFound(exception))
                .findAny().orElse(new DefaultErrorSolution());
    }
}