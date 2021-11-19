package org.example;

import org.example.ErrorHandler.Error;
import org.example.ErrorHandler.Handlers.DefaultError;
import org.example.ErrorHandler.Handlers.FileNotFound;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SafeInvokerTest {

    @InjectMocks
    private SafeInvoker safeInvoker;

    @Test
    public void invokeShouldNotThrowErrors() {
        Assertions.assertDoesNotThrow(
                () -> safeInvoker.invoke(() -> {throw new Exception();})
        );
    }

    @Test
    public void getErrorHandlerShouldReturnDefaultErrorWhenNotInList() {
        Error errorHandler = safeInvoker.getErrorHandler(new Exception());
        assertEquals(DefaultError.class, errorHandler.getClass());
    }

    @Test
    public void getErrorHandlerShouldReturnCorrectHandlerFromList() {
        Error errorHandler = safeInvoker.getErrorHandler(new FileNotFoundException());
        assertEquals(FileNotFound.class, errorHandler.getClass());
    }

}