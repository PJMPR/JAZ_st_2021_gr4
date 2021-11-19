package org.example.Protocols;

import org.example.Functions.Functions;

public interface ErrorInterface {
    void protocol(Functions method, Exception exception);
    boolean ifProtocol(Exception error);
    String message();
}