package org.example.Protocols.ErrorProtocols;

import org.example.Protocols.Error;
import org.example.Functions.Functions;
import java.sql.SQLException;

public class SQLExceptionProtocol extends Error {
    public SQLExceptionProtocol() {
        super(SQLExceptionProtocol.class);
    }

    @Override
    public void protocol(Functions method, Exception exception) {
        if (retry(method, 5, 5000))
            defaultMessage(exception);
    }

    @Override
    public boolean ifProtocol(Exception error) {
        return error instanceof SQLException;
    }

    @Override
    public String message() {
        return "Database connection unsuccesful";
    }
}