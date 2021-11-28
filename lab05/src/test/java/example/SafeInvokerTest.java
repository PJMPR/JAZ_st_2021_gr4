package example;

import org.junit.Test;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public class SafeInvokerTest {
    SafeInvoker safeInvoker = new SafeInvoker();

    @Test
    public void NoClassNotFoundException(){
        safeInvoker.invoke(() -> {throw new ClassNotFoundException();});
    }

    @Test
    public void NoFileNotFoundException(){
        safeInvoker.invoke(() -> {throw new FileNotFoundException();});
    }

    @Test
    public void NoSQLException(){
        safeInvoker.invoke(() -> {throw new SQLException();});
    }

    @Test
    public void NoTimeoutException(){
        safeInvoker.invoke(() -> {throw new TimeoutException();});
    }

}