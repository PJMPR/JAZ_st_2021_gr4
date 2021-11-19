package org.example.Protocols.ErrorProtocols;

import org.example.Protocols.Error;
import org.example.Functions.Functions;

import java.io.FileNotFoundException;

public class FileNotFoundExceptionProtocol extends Error {
    public FileNotFoundExceptionProtocol() {
        super(FileNotFoundExceptionProtocol.class);
    }

    @Override
    public void protocol(Functions method, Exception exception) {
        if (retry(method, 5, 1000)) {
            defaultMessage(exception);
        }
    }

    @Override
    public boolean ifProtocol(Exception error) {
        return error instanceof FileNotFoundException;
    }

    @Override
    public String message() {
        return "File not found";
    }
}
