package aurafarmer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import aurafarmer.task.Deadline;
import aurafarmer.task.Event;
import aurafarmer.task.Task;
import aurafarmer.task.Todo;

/**
 * Handles loading and saving of tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new Storage with the given file path.
     *
     * @param filePath Path to the file used for storing tasks.
     */
    public Storage(String filePath) {
        assert filePath != null : "File path should not be null";
        assert !filePath.isEmpty() : "File path should not be empty";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the data file.
     *
     * @return List of tasks loaded from the file.
     * @throws AuraFarmerException If the file cannot be read.
     */
    public ArrayList<Task> load() throws AuraFarmerException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            scanner.close();
        } catch (IOException e) {
            throw new AuraFarmerException("couldn't load tasks from file: " + e.getMessage());
        }

        return tasks;
    }

    private Task parseTask(String line) throws AuraFarmerException {
        try {
            String[] parts = line.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task;
            switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                String by = parts[3];
                task = new Deadline(description, by);
                break;
            case "E":
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
                break;
            default:
                throw new AuraFarmerException("corrupted data: unknown task type");
            }

            assert task != null : "Parsed task should not be null";
            if (isDone) {
                task.markAsDone();
            }
            return task;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AuraFarmerException("corrupted data: invalid format");
        }
    }

    /**
     * Saves the given list of tasks to the data file.
     *
     * @param tasks List of tasks to save.
     * @throws AuraFarmerException If the file cannot be written.
     */
    public void save(ArrayList<Task> tasks) throws AuraFarmerException {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();

            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                writer.write(taskToFileFormat(task) + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new AuraFarmerException("couldn't save tasks to file: " + e.getMessage());
        }
    }

    private String taskToFileFormat(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return "D | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + " | " + d.getBy();
        } else if (task instanceof Event) {
            Event e = (Event) task;
            return "E | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription()
                    + " | " + e.getFrom() + " | " + e.getTo();
        }
        return "";
    }
}
