package filesJob;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class FileLoggerTest {
    @Test
    void fileLoggerCreationTest() {
        FileLoggerConfiguration configuration = new FileLoggerConfiguration("logs", LoggingLevel.INFO, 1000);
        FileLogger logger = new FileLogger(configuration);
        assertNotNull(logger);
        assertEquals("logs", configuration.directoryPath());
        assertEquals(LoggingLevel.INFO, configuration.level());
        assertEquals(1000, configuration.maxSize());
    }
}
