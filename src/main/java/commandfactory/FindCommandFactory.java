package commandfactory;

import command.Command;
import command.FindCommand;
import exception.SyncException;

/**
 * Factory class responsible for creating a FindCommand.
 * This factory creates a command that performs a search based on the provided keyword.
 */
public class FindCommandFactory implements CommandFactory {
    private final String keyword;

    /**
     * Constructor to initialize the factory with the keyword used for searching.
     *
     * @param keyword The keyword to search for
     */
    public FindCommandFactory(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Creates a FindCommand based on the provided keyword.
     *
     * @return A new FindCommand that performs a search with the specified keyword
     * @throws SyncException If the keyword is null or empty
     */
    public Command createCommand() throws SyncException {
        assert keyword != null : "Keyword should not be null";
        assert !keyword.isEmpty() : "Keyword should not be empty";

        return new FindCommand(keyword);
    }
}
