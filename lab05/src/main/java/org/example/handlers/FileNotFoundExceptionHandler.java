package org.example.handlers;

import org.example.suppliers.FunctionSupplier;

import java.io.FileNotFoundException;

public class FileNotFoundExceptionHandler extends Responder{

    public FileNotFoundExceptionHandler() {
        super(FileNotFoundExceptionHandler.class);
    }

    @Override
    public void handle(FunctionSupplier method, Exception exception) {
        if(retry(method,5, 2000)){
            defaultResponse(exception);
        }
    }

    @Override
    public boolean ifHandles(Exception exception) {
        return exception instanceof FileNotFoundException;
    }

    @Override
    public String message() {
        return "File not found.";
    }
}
