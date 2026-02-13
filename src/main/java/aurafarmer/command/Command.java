package aurafarmer.command;

import aurafarmer.AuraFarmerException;
import aurafarmer.Storage;
import aurafarmer.TaskList;
import aurafarmer.Ui;

/**
 * Represents an abstract command that can be executed by the chatbot.
 */
public abstract class Command {

    /**
     * Executes this command.
     *
     * @param tasks TaskList to operate on.
     * @param ui Ui to display output.
     * @param storage Storage to save changes.
     * @throws AuraFarmerException If execution fails.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AuraFarmerException;

    /**
     * Returns whether this command should cause the application to exit.
     *
     * @return True if this is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
