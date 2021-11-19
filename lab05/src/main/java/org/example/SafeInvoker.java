package org.example;

import org.example.ErrorHandler.Error;
import org.example.ErrorHandler.Handlers.*;
import org.example.ErrorHandler.ExecutionProviderInterface;

import java.util.List;

public class SafeInvoker {
    private final List<Error> errorHandlers = List.of(
            new FileNotFound(),
            new IOException(),
            new ConnectionLost()
    );

    public Error getErrorHandler(Exception exception){
        return errorHandlers.stream()
                .filter(errorHandler -> errorHandler.canHandle(exception))
                .findAny().orElse(new DefaultError());
    }

    public void invoke(ExecutionProviderInterface executionProviderInterface){
        try{
            executionProviderInterface.execute();
        }catch (Exception exception){
            getErrorHandler(exception).handle(executionProviderInterface, exception);
        }
    }
}