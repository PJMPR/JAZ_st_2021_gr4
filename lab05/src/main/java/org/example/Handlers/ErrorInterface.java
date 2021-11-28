package org.example.Handlers;

import org.example.Suppliers.FunctionSupplier;

public interface ErrorInterface {
    void handle(FunctionSupplier method, Exception exception);
    boolean ifHandle(Exception error);
    String returnMessage();
}