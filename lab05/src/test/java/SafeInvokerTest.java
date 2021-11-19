
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

public class SafeInvokerTest {

    SafeInvoker safeInvoker = new SafeInvoker();

    @Test
    public void CheckIfReturnsClassNotFoundException(){
        safeInvoker.invoke(()->{throw new ClassNotFoundException();});
    }@Test
    public void CheckIfReturnsFileNotFoundException(){
        safeInvoker.invoke(()->{throw new FileNotFoundException();});
    }@Test
    public void CheckIfReturnsCSQLException(){
        safeInvoker.invoke(()->{throw new SQLException();});
    }@Test
    public void CheckIfReturnsCTimeoutException(){
        safeInvoker.invoke(()->{throw new TimeoutException();});
    }@Test
    public void ShouldNotThrowErrors(){
        Assertions.assertDoesNotThrow(() ->
        safeInvoker.invoke(()->{throw new Exception();}));
    }

}
