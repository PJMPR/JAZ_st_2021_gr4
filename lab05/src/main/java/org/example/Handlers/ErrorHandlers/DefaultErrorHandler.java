package org.example.Handlers.ErrorHandlers;

import org.example.Handlers.Error;
import org.example.Suppliers.FunctionSupplier;

public class DefaultErrorHandler extends Error {
    public DefaultErrorHandler() {
        super(DefaultErrorHandler.class);
    }

    @Override
    public void handle(FunctionSupplier method, Exception exception){
        defaultResponse(exception);
    };

    @Override
    public boolean ifHandle(Exception error) {
        return error != null;
    }

    @Override
    public String returnMessage() {
        return "Default error";
    }
}