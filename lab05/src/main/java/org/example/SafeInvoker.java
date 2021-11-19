package org.example;

import org.example.handlers.*;
import org.example.suppliers.FunctionSupplier;

import java.util.List;

public class SafeInvoker {
    private final List<Responder> handlers = List.of(
            new FileNotFoundExceptionHandler(),
            new IOExceptionHandler(),
            new SQLExceptionHandler()
    );

    public Responder findExceptionHandler(Exception exception){
        return handlers.stream()
                .filter(handler -> handler.ifHandles(exception)).findAny()
                .orElse(new DefaultExceptionHandler());
    }

    public void invoke(FunctionSupplier functionSupplier){
        try{
            functionSupplier.execute();
        } catch (Exception exception) {
            findExceptionHandler(exception).handle(functionSupplier,exception);
        }
    }
}
