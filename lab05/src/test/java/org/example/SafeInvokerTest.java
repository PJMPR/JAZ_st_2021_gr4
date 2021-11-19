package org.example;

import org.example.Protocols.Error;
import org.example.Protocols.ErrorProtocols.DefaultErrorProtocol;
import org.example.Protocols.ErrorProtocols.FileNotFoundExceptionProtocol;
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
    public void findErrorHandlerShouldReturnErrorHandlerWhenErrorIsOnTheList() {
        Error errorHandler = safeInvoker.pickErrorProtocol(new FileNotFoundException());
        assertEquals(FileNotFoundExceptionProtocol.class, errorHandler.getClass());
    }


    @Test
    public void findErrorHandlerShouldReturnDefaultErrorHandlerWhenErrorIsNotInTheList() {
        Error errorHandler = safeInvoker.pickErrorProtocol(new Exception());
        assertEquals(DefaultErrorProtocol.class, errorHandler.getClass());
    }

    @Test
    public void invokeShouldNotThrowErrors() {
        Assertions.assertDoesNotThrow(
                () -> safeInvoker.invoke(() -> {throw new Exception();})
        );
    }
}