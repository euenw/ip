package aurafarmer;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import aurafarmer.command.ByeCommand;
import aurafarmer.command.DeadlineCommand;
import aurafarmer.command.DeleteCommand;
import aurafarmer.command.EventCommand;
import aurafarmer.command.ListCommand;
import aurafarmer.command.MarkCommand;
import aurafarmer.command.TodoCommand;
import aurafarmer.command.UnmarkCommand;

public class ParserTest {

    @Test
    public void parse_byeCommand_success() throws AuraFarmerException {
        assertInstanceOf(ByeCommand.class, Parser.parse("bye"));
    }

    @Test
    public void parse_listCommand_success() throws AuraFarmerException {
        assertInstanceOf(ListCommand.class, Parser.parse("list"));
    }

    @Test
    public void parse_todoCommand_success() throws AuraFarmerException {
        assertInstanceOf(TodoCommand.class, Parser.parse("todo read book"));
    }

    @Test
    public void parse_todoEmptyDescription_throwsException() {
        assertThrows(AuraFarmerException.class, () -> Parser.parse("todo"));
        assertThrows(AuraFarmerException.class, () -> Parser.parse("todo    "));
    }

    @Test
    public void parse_deadlineCommand_success() throws AuraFarmerException {
        assertInstanceOf(DeadlineCommand.class, Parser.parse("deadline return book /by 2024-01-01"));
    }

    @Test
    public void parse_deadlineMissingBy_throwsException() {
        assertThrows(AuraFarmerException.class, () -> Parser.parse("deadline return book"));
    }

    @Test
    public void parse_deadlineEmptyBy_throwsException() {
        assertThrows(AuraFarmerException.class, () -> Parser.parse("deadline return book /by "));
    }

    @Test
    public void parse_eventCommand_success() throws AuraFarmerException {
        assertInstanceOf(EventCommand.class,
                Parser.parse("event project meeting /from 2024-01-01 /to 2024-01-02"));
    }

    @Test
    public void parse_eventMissingFromOrTo_throwsException() {
        assertThrows(AuraFarmerException.class, () -> Parser.parse("event meeting /from Monday"));
        assertThrows(AuraFarmerException.class, () -> Parser.parse("event meeting /to Tuesday"));
    }

    @Test
    public void parse_markCommand_success() throws AuraFarmerException {
        assertInstanceOf(MarkCommand.class, Parser.parse("mark 1"));
    }

    @Test
    public void parse_markMissingIndex_throwsException() {
        assertThrows(AuraFarmerException.class, () -> Parser.parse("mark"));
    }

    @Test
    public void parse_markInvalidIndex_throwsException() {
        assertThrows(AuraFarmerException.class, () -> Parser.parse("mark abc"));
    }

    @Test
    public void parse_unmarkCommand_success() throws AuraFarmerException {
        assertInstanceOf(UnmarkCommand.class, Parser.parse("unmark 1"));
    }

    @Test
    public void parse_unmarkMissingIndex_throwsException() {
        assertThrows(AuraFarmerException.class, () -> Parser.parse("unmark"));
    }

    @Test
    public void parse_deleteCommand_success() throws AuraFarmerException {
        assertInstanceOf(DeleteCommand.class, Parser.parse("delete 1"));
    }

    @Test
    public void parse_deleteMissingIndex_throwsException() {
        assertThrows(AuraFarmerException.class, () -> Parser.parse("delete"));
    }

    @Test
    public void parse_deleteInvalidIndex_throwsException() {
        assertThrows(AuraFarmerException.class, () -> Parser.parse("delete xyz"));
    }

    @Test
    public void parse_unknownCommand_throwsException() {
        assertThrows(AuraFarmerException.class, () -> Parser.parse("blah"));
    }
}
