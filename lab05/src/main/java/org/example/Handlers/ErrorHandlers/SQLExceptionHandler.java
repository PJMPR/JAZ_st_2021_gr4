package org.example.Handlers.ErrorHandlers;

import org.example.Handlers.Error;
import org.example.Suppliers.FunctionSupplier;
import java.sql.SQLException;

public class SQLExceptionHandler extends Error {
    public SQLExceptionHandler() {
        super(SQLExceptionHandler.class);
    }

    @Override
    public void handle(FunctionSupplier method, Exception exception) {
        if(retry(method, 5, 5000)){
            defaultResponse(exception);
        }
    }

    @Override
    public boolean ifHandle(Exception error) {
        return error instanceof SQLException;
    }

    @Override
    public String returnMessage() {
        return "Database connection interrupted";
    }
}