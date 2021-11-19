package org.example.handlers;

import org.example.suppliers.FunctionSupplier;

public class DefaultExceptionHandler extends Responder{

    public DefaultExceptionHandler() {
        super(DefaultExceptionHandler.class);
    }

    @Override
    public void handle(FunctionSupplier method, Exception exception) {
        defaultResponse(exception);
    }

    @Override
    public boolean ifHandles(Exception exception) {
        return exception != null;
    }

    @Override
    public String message() {
        return "Default exception";
    }
}
