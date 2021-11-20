package org.example.Exception;

import org.example.Runner;

public interface ExceptionInterface {
    void useSolution(Runner method, Exception exception);
    boolean ifSolutionFound(Exception error);
    String feedback();
}