package aurafarmer.command;

import aurafarmer.AuraFarmerException;
import aurafarmer.Storage;
import aurafarmer.TaskList;
import aurafarmer.Ui;
import aurafarmer.task.Deadline;
import aurafarmer.task.Task;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    private String description;
    private String by;

    /**
     * Creates a new DeadlineCommand with the given description and deadline.
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AuraFarmerException {
        Task task = new Deadline(description, by);
        tasks.addTask(task);
        ui.showTaskAdded(task, tasks.size());
        storage.save(tasks.getTasks());
    }
}
