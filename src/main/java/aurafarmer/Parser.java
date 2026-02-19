package aurafarmer;

import aurafarmer.command.ByeCommand;
import aurafarmer.command.Command;
import aurafarmer.command.DeadlineCommand;
import aurafarmer.command.DeleteCommand;
import aurafarmer.command.EventCommand;
import aurafarmer.command.FindCommand;
import aurafarmer.command.ListCommand;
import aurafarmer.command.MarkCommand;
import aurafarmer.command.TodoCommand;
import aurafarmer.command.UnmarkCommand;

/**
 * Parses user input and returns the corresponding command.
 */
public class Parser {

    /**
     * Parses the given user input string and returns the corresponding Command.
     *
     * @param fullCommand Full user input string.
     * @return Command object corresponding to the user input.
     * @throws AuraFarmerException If the input is invalid or unrecognized.
     */
    public static Command parse(String fullCommand) throws AuraFarmerException {
        assert fullCommand != null : "Command string should not be null";
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        switch (commandWord) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(parseTaskIndex(arguments, "mark"));
        case "unmark":
            return new UnmarkCommand(parseTaskIndex(arguments, "unmark"));
        case "delete":
            return new DeleteCommand(parseTaskIndex(arguments, "delete"));
        case "todo":
            return parseTodoCommand(arguments);
        case "deadline":
            return parseDeadlineCommand(arguments);
        case "event":
            return parseEventCommand(arguments);
        case "find":
            return parseFindCommand(arguments);
        default:
            throw new AuraFarmerException("i don't know what that means, you're losing aura");
        }
    }

    private static int parseTaskIndex(String arguments, String commandName) throws AuraFarmerException {
        if (arguments.trim().isEmpty()) {
            throw new AuraFarmerException(commandName + " what? give me a task number");
        }
        try {
            return Integer.parseInt(arguments.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new AuraFarmerException("that's not a valid number, no aura for you");
        }
    }

    private static Command parseTodoCommand(String arguments) throws AuraFarmerException {
        if (arguments.trim().isEmpty()) {
            throw new AuraFarmerException("the description of a todo cannot be empty, that's negative aura");
        }
        return new TodoCommand(arguments);
    }

    private static Command parseDeadlineCommand(String arguments) throws AuraFarmerException {
        if (arguments.trim().isEmpty() || !arguments.contains(" /by ")) {
            throw new AuraFarmerException("deadline needs a description and /by time, that's how you farm aura");
        }
        int byIndex = arguments.indexOf(" /by ");
        if (byIndex <= 0) {
            throw new AuraFarmerException("deadline needs a description before /by");
        }
        String description = arguments.substring(0, byIndex);
        String by = arguments.substring(byIndex + " /by ".length());
        if (by.trim().isEmpty()) {
            throw new AuraFarmerException("when is the deadline? give me the /by time");
        }
        return new DeadlineCommand(description, by);
    }

    private static Command parseEventCommand(String arguments) throws AuraFarmerException {
        if (arguments.trim().isEmpty() || !arguments.contains(" /from ") || !arguments.contains(" /to ")) {
            throw new AuraFarmerException("event needs a description, /from and /to times");
        }
        int fromIndex = arguments.indexOf(" /from ");
        int toIndex = arguments.indexOf(" /to ");
        if (fromIndex <= 0) {
            throw new AuraFarmerException("event needs a description before /from");
        }
        String description = arguments.substring(0, fromIndex);
        String from = arguments.substring(fromIndex + " /from ".length(), toIndex);
        String to = arguments.substring(toIndex + " /to ".length());
        if (from.trim().isEmpty() || to.trim().isEmpty()) {
            throw new AuraFarmerException("event needs both /from and /to times filled in");
        }
        return new EventCommand(description, from, to);
    }

    private static Command parseFindCommand(String arguments) throws AuraFarmerException {
        if (arguments.trim().isEmpty()) {
            throw new AuraFarmerException("find what? give me a keyword");
        }
        return new FindCommand(arguments.trim());
    }
}
