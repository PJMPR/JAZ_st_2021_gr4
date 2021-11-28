package org.example.Handlers.ErrorHandlers;

import org.example.Handlers.Error;
import org.example.Suppliers.FunctionSupplier;

import java.io.IOException;

public class IOExceptionHandler extends Error {
    public IOExceptionHandler() {
        super(IOExceptionHandler.class);
    }

    @Override
    public void handle(FunctionSupplier method, Exception exception) {
        if(retry(method, 10, 1000)){
            defaultResponse(exception);
        }
    }

    @Override
    public boolean ifHandle(Exception error) {
        return error instanceof IOException;
    }

    @Override
    public String returnMessage() {
        return "Resource error";
    }
}