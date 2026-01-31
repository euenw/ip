package aurafarmer;

import aurafarmer.command.ByeCommand;
import aurafarmer.command.Command;
import aurafarmer.command.DeadlineCommand;
import aurafarmer.command.DeleteCommand;
import aurafarmer.command.EventCommand;
import aurafarmer.command.ListCommand;
import aurafarmer.command.MarkCommand;
import aurafarmer.command.TodoCommand;
import aurafarmer.command.UnmarkCommand;

public class Parser {

    public static Command parse(String fullCommand) throws AuraFarmerException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];

        switch (commandWord) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return parseMarkCommand(fullCommand);
        case "unmark":
            return parseUnmarkCommand(fullCommand);
        case "todo":
            return parseTodoCommand(fullCommand);
        case "deadline":
            return parseDeadlineCommand(fullCommand);
        case "event":
            return parseEventCommand(fullCommand);
        case "delete":
            return parseDeleteCommand(fullCommand);
        default:
            throw new AuraFarmerException("i don't know what that means, you're losing aura");
        }
    }

    private static Command parseMarkCommand(String input) throws AuraFarmerException {
        if (input.length() <= 5) {
            throw new AuraFarmerException("mark what? give me a task number");
        }
        try {
            int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
            return new MarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new AuraFarmerException("that's not a valid number, no aura for you");
        }
    }

    private static Command parseUnmarkCommand(String input) throws AuraFarmerException {
        if (input.length() <= 7) {
            throw new AuraFarmerException("unmark what? give me a task number");
        }
        try {
            int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
            return new UnmarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new AuraFarmerException("that's not a valid number, no aura for you");
        }
    }

    private static Command parseTodoCommand(String input) throws AuraFarmerException {
        if (input.length() <= 5 || input.substring(5).trim().isEmpty()) {
            throw new AuraFarmerException("the description of a todo cannot be empty, that's negative aura");
        }
        String description = input.substring(5);
        return new TodoCommand(description);
    }

    private static Command parseDeadlineCommand(String input) throws AuraFarmerException {
        if (input.length() <= 9 || !input.contains(" /by ")) {
            throw new AuraFarmerException("deadline needs a description and /by time, that's how you farm aura");
        }
        String rest = input.substring(9);
        int byIndex = rest.indexOf(" /by ");
        if (byIndex <= 0) {
            throw new AuraFarmerException("deadline needs a description before /by");
        }
        String description = rest.substring(0, byIndex);
        String by = rest.substring(byIndex + 5);
        if (by.trim().isEmpty()) {
            throw new AuraFarmerException("when is the deadline? give me the /by time");
        }
        return new DeadlineCommand(description, by);
    }

    private static Command parseEventCommand(String input) throws AuraFarmerException {
        if (input.length() <= 6 || !input.contains(" /from ") || !input.contains(" /to ")) {
            throw new AuraFarmerException("event needs a description, /from and /to times");
        }
        String rest = input.substring(6);
        int fromIndex = rest.indexOf(" /from ");
        int toIndex = rest.indexOf(" /to ");
        if (fromIndex <= 0) {
            throw new AuraFarmerException("event needs a description before /from");
        }
        String description = rest.substring(0, fromIndex);
        String from = rest.substring(fromIndex + 7, toIndex);
        String to = rest.substring(toIndex + 5);
        if (from.trim().isEmpty() || to.trim().isEmpty()) {
            throw new AuraFarmerException("event needs both /from and /to times filled in");
        }
        return new EventCommand(description, from, to);
    }

    private static Command parseDeleteCommand(String input) throws AuraFarmerException {
        if (input.length() <= 7) {
            throw new AuraFarmerException("delete what? give me a task number");
        }
        try {
            int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new AuraFarmerException("that's not a valid number, no aura for you");
        }
    }
}
