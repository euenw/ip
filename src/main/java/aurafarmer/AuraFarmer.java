package aurafarmer;

import aurafarmer.command.Command;

/**
 * Represents the main chatbot application.
 */
public class AuraFarmer {

    private static final String DATA_FILE_PATH = "data/aurafarmer.txt";

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
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (AuraFarmerException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.close();
    }

    /**
     * Generates a response for the given user input.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage);
            if (command.isExit()) {
                return "aura: next time you return, return with more aura";
            }
        } catch (AuraFarmerException e) {
            return "aura: oops! " + e.getMessage();
        }
        return ui.getLastResponse();
    }

    public static void main(String[] args) {
        new AuraFarmer(DATA_FILE_PATH).run();
    }
}
