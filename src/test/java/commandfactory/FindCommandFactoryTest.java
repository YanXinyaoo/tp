package commandfactory;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import command.Command;
import command.FindCommand;
import exception.SyncException;
import logger.EventSyncLogger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class FindCommandFactoryTest {
    @BeforeAll
    static void setupLogger() {
        // Initialize the logger before running any tests
        EventSyncLogger.setupLogger();
    }

    @Test
    public void testCreateCommand_validKeyword_returnsFindCommand() throws SyncException {
        String keyword = "apple";
        FindCommandFactory factory = new FindCommandFactory(keyword);
        Command command = factory.createCommand();

        assertTrue(command instanceof FindCommand, "Expected instance of FindCommand");
    }

    @Test
    public void testCreateCommand_nullKeyword_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> {
            FindCommandFactory factory = new FindCommandFactory(null);
            factory.createCommand();
        });
    }

    @Test
    public void testCreateCommand_emptyKeyword_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> {
            FindCommandFactory factory = new FindCommandFactory("");
            factory.createCommand();
        });
    }
}

