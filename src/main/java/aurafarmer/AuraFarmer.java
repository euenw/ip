package aurafarmer;

import aurafarmer.command.Command;

/**
 * Represents the main chatbot application.
 */
public class AuraFarmer {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new AuraFarmer instance and loads tasks from the given file path.
     *
     * @param filePath Path to the data file for storing tasks.
     */
    public AuraFarmer(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (AuraFarmerException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the chatbot, reading and executing commands until exit.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (AuraFarmerException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.close();
    }

    public static void main(String[] args) {
        new AuraFarmer("data/aurafarmer.txt").run();
    }
}
