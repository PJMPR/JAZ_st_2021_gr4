package org.example.ErrorHandler.Handlers;

import org.example.ErrorHandler.Error;
import org.example.ErrorHandler.ExecutionProviderInterface;

import java.io.FileNotFoundException;

public class FileNotFound extends Error {
    public FileNotFound() {
        super(FileNotFound.class);
    }

    @Override
    public void handle(ExecutionProviderInterface method, Exception exception) {
        if (retry(method, 2, 1000)) {
            defaultAction(exception);
        }
    }

    @Override
    public boolean canHandle(Exception error) {
        return error instanceof FileNotFoundException;
    }

    @Override
    public String returnMsg() {
        return "Error, file not found.";
    }
}
