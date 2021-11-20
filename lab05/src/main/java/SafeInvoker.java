import Errors.ClassNotFoundExceptionHandler;
import Errors.FileNotFoundExceptionHandler;
import Errors.SQLExceptionHandler;
import Errors.TimeoutExceptionHandler;
import Handlers.ErrorHandler;
import Handlers.UnsafeMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class SafeInvoker {
    List<ErrorHandler> errorHandlers = new ArrayList<>(List.of(
            new ClassNotFoundExceptionHandler(),
            new TimeoutExceptionHandler(),
            new FileNotFoundExceptionHandler(),
            new SQLExceptionHandler()
    ));

    public void invoke(UnsafeMethod method){

        try {
            method.execute();
        } catch (Exception err){
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            errorHandlers.stream()
                    .filter(errorHandler -> errorHandler.canHandle(err))
                    .forEach(errorHandler -> atomicBoolean.set(true));
        }

    }
}