package org.example.handlers;

import org.example.suppliers.FunctionSupplier;

import java.sql.SQLException;

public class SQLExceptionHandler extends Responder{
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
    public boolean ifHandles(Exception exception) {
        return exception instanceof SQLException;
    }

    @Override
    public String message() {
        return "Database connection failed";
    }
}
