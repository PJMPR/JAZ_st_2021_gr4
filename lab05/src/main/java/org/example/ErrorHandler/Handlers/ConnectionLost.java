package org.example.ErrorHandler.Handlers;

import org.example.ErrorHandler.Error;
import org.example.ErrorHandler.ExecutionProviderInterface;
import java.sql.SQLException;

public class ConnectionLost extends Error {
    public ConnectionLost() {
        super(ConnectionLost.class);
    }

    @Override
    public void handle(ExecutionProviderInterface method, Exception exception) {
        if (retry(method, 10, 10000)){
            defaultAction(exception);
        }
    }

    @Override
    public boolean canHandle(Exception error) {
        return error instanceof SQLException;
    }

    @Override
    public String returnMsg() {
        return "Database connection interrupted or lost";
    }
}