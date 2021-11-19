package org.example.ErrorHandler;

public interface ErrorInterface {
    void handle(ExecutionProviderInterface method, Exception exception);
    boolean canHandle(Exception error);
    String returnMsg();
}