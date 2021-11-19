package org.example.handlers;

import org.example.suppliers.FunctionSupplier;

import java.io.IOException;

public class IOExceptionHandler extends Responder{

    public IOExceptionHandler() {
        super(IOExceptionHandler.class);
    }

    @Override
    public void handle(FunctionSupplier method, Exception exception) {
        if(retry(method,10,2000)){
            defaultResponse(exception);
        }
    }

    @Override
    public boolean ifHandles(Exception exception) {
        return exception instanceof IOException;
    }

    @Override
    public String message() {
        return "Resource error";
    }
}
