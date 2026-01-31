package aurafarmer.command;

import aurafarmer.AuraFarmerException;
import aurafarmer.Storage;
import aurafarmer.TaskList;
import aurafarmer.Ui;
import aurafarmer.task.Deadline;
import aurafarmer.task.Task;

public class DeadlineCommand extends Command {
    private String description;
    private String by;

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
