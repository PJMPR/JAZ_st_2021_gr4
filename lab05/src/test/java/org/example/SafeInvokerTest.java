package org.example;

import org.example.handlers.DefaultExceptionHandler;
import org.example.handlers.FileNotFoundExceptionHandler;
import org.example.handlers.Responder;
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
    public void findExceptionHandlerShouldReturnErrorHandlerWhenExceptionIsOnTheList() {
        Responder errorHandler = safeInvoker.findExceptionHandler(new FileNotFoundException());
        assertEquals(FileNotFoundExceptionHandler.class, errorHandler.getClass());
    }

    @Test
    public void findExceptionHandlerShouldReturnDefaultErrorHandlerWhenExceptionIsNotInTheList() {
        Responder errorHandler = safeInvoker.findExceptionHandler(new Exception());
        assertEquals(DefaultExceptionHandler.class, errorHandler.getClass());
    }

    @Test
    public void invokeShouldNotThrowErrors() {
        Assertions.assertDoesNotThrow(
                () -> safeInvoker.invoke(() -> {throw new Exception();}));
    }
}