package aurafarmer.command;

import aurafarmer.AuraFarmerException;
import aurafarmer.Storage;
import aurafarmer.TaskList;
import aurafarmer.Ui;
import aurafarmer.task.Task;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AuraFarmerException {
        Task task = tasks.deleteTask(taskIndex);
        ui.showTaskDeleted(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
