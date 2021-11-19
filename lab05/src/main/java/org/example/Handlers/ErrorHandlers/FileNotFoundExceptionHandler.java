package org.example.Handlers.ErrorHandlers;

import org.example.Handlers.Error;
import org.example.Suppliers.FunctionSupplier;

import java.io.FileNotFoundException;

public class FileNotFoundExceptionHandler extends Error {
    public FileNotFoundExceptionHandler() {
        super(FileNotFoundExceptionHandler.class);
    }

    @Override
    public void handle(FunctionSupplier method, Exception exception) {
        if ( retry(method, 5, 1000) ) {
            defaultResponse(exception);
        }
    }

    @Override
    public boolean ifHandle(Exception error) {
        return error instanceof FileNotFoundException;
    }

    @Override
    public String returnMessage() {
        return "Error. File not found.";
    }
}
