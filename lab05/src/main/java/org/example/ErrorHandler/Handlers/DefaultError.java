package org.example.ErrorHandler.Handlers;

import org.example.ErrorHandler.Error;
import org.example.ErrorHandler.ExecutionProviderInterface;

public class DefaultError extends Error {
    public DefaultError() {
        super(DefaultError.class);
    }

    @Override
    public void handle(ExecutionProviderInterface method, Exception exception){
        defaultAction(exception);
    };

    @Override
    public boolean canHandle(Exception error) {
        return error != null;
    }

    @Override
    public String returnMsg() {
        return "Unknown error";
    }
}