package aurafarmer.command;

import aurafarmer.AuraFarmerException;
import aurafarmer.Storage;
import aurafarmer.TaskList;
import aurafarmer.Ui;

/**
 * Represents a command to sort tasks chronologically.
 */
public class SortCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AuraFarmerException {
        tasks.sortTasks();
        ui.showSorted(tasks);
        storage.save(tasks.getTasks());
    }
}
