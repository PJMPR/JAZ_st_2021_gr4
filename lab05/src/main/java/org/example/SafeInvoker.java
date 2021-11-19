package org.example;

import org.example.Handlers.Error;
import org.example.Handlers.ErrorHandlers.*;
import org.example.Suppliers.FunctionSupplier;

import java.util.List;

public class SafeInvoker {
    private final List<Error> errorHandlers = List.of(
            new FileNotFoundExceptionHandler(),
            new IOExceptionHandler(),
            new SQLExceptionHandler()
    );

    public Error findErrorHandler(Exception exception){
        return errorHandlers.stream()
                .filter(errorHandler -> errorHandler.ifHandle(exception))
                .findAny().orElse(new DefaultErrorHandler());
    }

    public void invoke(FunctionSupplier functionSupplier){
        try{
            functionSupplier.execute();
        }catch (Exception exception){
            findErrorHandler(exception).handle(functionSupplier, exception);
        }
    }
}