import errorhandling.DefaultStrategy;
import errorhandling.ErrorHandlingStrategy;
import errorhandling.RetryStrategy;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.EmptyStackException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SafeInvokerTest {

    @InjectMocks
    private SafeInvoker safeInvoker;

    @Test
    public void retry_strategy_chosen_for_SQLException() {
        ErrorHandlingStrategy strategy = safeInvoker.findHandlingStrategy(new SQLException());
        strategy.handle();
        assertEquals(RetryStrategy.class, strategy.getClass());
    }

    @Test
    public void default_strategy_chosen_for_unknown_error() {
        ErrorHandlingStrategy strategy = safeInvoker.findHandlingStrategy(new EmptyStackException());
        strategy.handle();
        assertEquals(DefaultStrategy.class, strategy.getClass());
    }

    @Test
    public void retry_strategy_chosen_for_FileNotFoundException() {
        ErrorHandlingStrategy strategy = safeInvoker.findHandlingStrategy(new FileNotFoundException());
        strategy.handle();
        assertEquals(RetryStrategy.class, strategy.getClass());
    }

    @Test
    public void invokeShouldNotThrowErrors() {
        Assertions.assertDoesNotThrow(
                () -> safeInvoker.invoke(() -> {})
        );
    }
}