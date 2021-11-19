package org.example.Protocols.ErrorProtocols;

import org.example.Protocols.Error;
import org.example.Functions.Functions;

import java.io.IOException;

public class IOExceptionProtocol extends Error {
    public IOExceptionProtocol() {
        super(IOExceptionProtocol.class);
    }

    @Override
    public void protocol(Functions method, Exception exception) {
        if(retry(method, 10, 1000)) {
            defaultMessage(exception);
        }
    }

    @Override
    public boolean ifProtocol(Exception error) {
        return error instanceof IOException;
    }

    @Override
    public String message() {
        return "Resource is invalid or corrupted";
    }
}