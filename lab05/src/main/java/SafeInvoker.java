import errorhandling.*;
import suppliers.Supplier;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;

public class SafeInvoker {

    private final  HashMap<Class, ErrorHandlingStrategy> errorHandlerHashMap = new HashMap<>(){{
        put(SQLException.class, new RetryStrategy("An SQLException occurred.", 3, 5));
        put(FileNotFoundException.class, new RetryStrategy("Could not find file with specified path.", 1, 5));
    }};

    public ErrorHandlingStrategy findHandlingStrategy(Exception e){
        return errorHandlerHashMap.containsKey(e.getClass()) ? errorHandlerHashMap.get(e.getClass()) : new DefaultStrategy(e);

    }

    public void invoke(Supplier method){
        try{
            method.execute();
        }catch (Exception e){
            ErrorHandlingStrategy errorHandlingStrategy = findHandlingStrategy(e);
            if (errorHandlingStrategy instanceof RetryStrategy) {
                ((RetryStrategy) errorHandlingStrategy).setMethod(method);
            }
            errorHandlingStrategy.handle();
        }
    }

}