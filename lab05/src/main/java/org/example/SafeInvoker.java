package org.example;

import org.example.Protocols.Error;
import org.example.Protocols.ErrorProtocols.*;
import org.example.Functions.Functions;

import java.util.List;

public class SafeInvoker {
    private final List<Error> errorProtocol = List.of(
            new FileNotFoundExceptionProtocol(),
            new IOExceptionProtocol(),
            new SQLExceptionProtocol()
    );

    public void invoke(Functions functions) {
        try {
            functions.execute();
        } catch (Exception exception) {
            pickErrorProtocol(exception).protocol(functions, exception);
        }
    }

    public Error pickErrorProtocol(Exception exception) {
        return errorProtocol.stream()
                .filter(errorProtocol -> errorProtocol.ifProtocol(exception))
                .findAny().orElse(new DefaultErrorProtocol());
    }
}