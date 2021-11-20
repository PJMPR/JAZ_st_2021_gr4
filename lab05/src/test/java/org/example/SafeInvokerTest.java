package org.example;

import org.example.Exception.ExceptionModel;
import org.example.Solutions.DefaultErrorSolution;
import org.example.Solutions.FileNotFoundSolution;
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
    public void check_if_return_exception_if_on_list() {
        ExceptionModel errorHandler = safeInvoker.findErrorHandler(new FileNotFoundException());
        assertEquals(FileNotFoundSolution.class, errorHandler.getClass());
    }

    @Test
    public void check_if_return_default_if_not_on_list() {
        ExceptionModel errorHandler = safeInvoker.findErrorHandler(new Exception());
        assertEquals(DefaultErrorSolution.class, errorHandler.getClass());
    }

    @Test
    public void check_if_invoke_not_throwing_exceptions() {
        Assertions.assertDoesNotThrow(
                () -> safeInvoker.invoke(() -> {throw new Exception();})
        );
    }
}