package org.example.Protocols.ErrorProtocols;

import org.example.Protocols.Error;
import org.example.Functions.Functions;

public class DefaultErrorProtocol extends Error {
    public DefaultErrorProtocol() {
        super(DefaultErrorProtocol.class);
    }

    @Override
    public void protocol(Functions method, Exception exception){
        defaultMessage(exception);
    };

    @Override
    public boolean ifProtocol(Exception error) {
        return error != null;
    }

    @Override
    public String message() {
        return "Default error";
    }
}