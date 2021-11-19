package org.example.ErrorHandler;

@FunctionalInterface
public interface ExecutionProviderInterface {
    void execute() throws Exception;
}