package aurafarmer.command;

import aurafarmer.AuraFarmerException;
import aurafarmer.Storage;
import aurafarmer.TaskList;
import aurafarmer.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AuraFarmerException;

    public boolean isExit() {
        return false;
    }
}
