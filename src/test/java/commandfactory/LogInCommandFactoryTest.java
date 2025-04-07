package commandfactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import command.Command;
import command.LoginCommand;
import exception.SyncException;
import logger.EventSyncLogger;
import participant.Participant;
import participant.ParticipantManager;
import storage.UserStorage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class LogInCommandFactoryTest {

    private LoginCommandFactory factory;
    private ParticipantManager participantManager;

    @BeforeAll
    static void setupLogger() {
        // Initialize the logger before running any tests
        EventSyncLogger.setupLogger();
    }

    @BeforeEach
    void setUp() throws SyncException {
        UserStorage userStorage = new UserStorage("./data/test-users.txt");
        participantManager = new ParticipantManager(new ArrayList<>(), null, userStorage);
        factory = new LoginCommandFactory(participantManager);
    }

    @Test
    public void testCreateCommand_NoUserLoggedIn_ReturnsLoginCommand() throws SyncException {
        participantManager.setCurrentUser(null);
        Command command = factory.createCommand();
        assertTrue(command instanceof LoginCommand);
    }

    @Test
    public void testCreateCommand_UserLoggedIn_ThrowsSyncException() throws SyncException {
        Participant testUser = new Participant("john_doe", "password123", Participant.AccessLevel.ADMIN, new ArrayList<>());
        participantManager.addNewUser(testUser);
        participantManager.setCurrentUser(testUser);
        SyncException exception = assertThrows(SyncException.class, factory::createCommand);
        assertEquals("You are already logged in. Please enter 'logout' to log out first.", exception.getMessage());
    }
}
