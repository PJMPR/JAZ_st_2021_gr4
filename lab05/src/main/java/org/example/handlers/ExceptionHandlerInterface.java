package org.example.handlers;

import org.example.suppliers.FunctionSupplier;

public interface ExceptionHandlerInterface {
    void handle(FunctionSupplier method, Exception exception);
    boolean ifHandles(Exception exception);
    String message();
}
