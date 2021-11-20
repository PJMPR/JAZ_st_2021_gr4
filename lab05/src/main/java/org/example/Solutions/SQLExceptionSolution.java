package org.example.Solutions;

import org.example.Exception.ExceptionModel;
import org.example.Runner;
import java.sql.SQLException;

public class SQLExceptionSolution extends ExceptionModel {
    public SQLExceptionSolution() {
        super(SQLExceptionSolution.class);
    }

    @Override
    public void useSolution(Runner method, Exception exception) {
        if(retry(method, 5, 5000)){
            defaultResponse(exception);
        }
    }

    @Override
    public boolean ifSolutionFound(Exception error) {
        return error instanceof SQLException;
    }

    @Override
    public String feedback() {
        return "Database connection interrupted";
    }
}