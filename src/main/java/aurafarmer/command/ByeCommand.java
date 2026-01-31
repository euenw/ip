package aurafarmer.command;

import aurafarmer.Storage;
import aurafarmer.TaskList;
import aurafarmer.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
