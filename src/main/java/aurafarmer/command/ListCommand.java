package aurafarmer.command;

import aurafarmer.Storage;
import aurafarmer.TaskList;
import aurafarmer.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
