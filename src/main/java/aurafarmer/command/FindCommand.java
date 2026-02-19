package aurafarmer.command;

import java.util.ArrayList;

import aurafarmer.AuraFarmerException;
import aurafarmer.Storage;
import aurafarmer.TaskList;
import aurafarmer.Ui;
import aurafarmer.task.Task;

/**
 * Represents a command to find tasks by keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AuraFarmerException {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        ui.showFoundTasks(matchingTasks);
    }
}
