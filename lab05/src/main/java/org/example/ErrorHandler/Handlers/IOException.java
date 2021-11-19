package org.example.ErrorHandler.Handlers;

import org.example.ErrorHandler.Error;
import org.example.ErrorHandler.ExecutionProviderInterface;

public class IOException extends Error {
    public IOException() {
        super(IOException.class);
    }

    @Override
    public void handle(ExecutionProviderInterface method, Exception exception) {
        if (retry(method, 5, 1000)){
            defaultAction(exception);
        }
    }

    @Override
    public boolean canHandle(Exception error) {
        return error instanceof java.io.IOException;
    }

    @Override
    public String returnMsg() {
        return "IOException error";
    }
}