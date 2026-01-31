package aurafarmer.command;

import aurafarmer.AuraFarmerException;
import aurafarmer.Storage;
import aurafarmer.TaskList;
import aurafarmer.Ui;
import aurafarmer.task.Task;

public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AuraFarmerException {
        Task task = tasks.unmarkTask(taskIndex);
        ui.showTaskUnmarked(task);
        storage.save(tasks.getTasks());
    }
}
