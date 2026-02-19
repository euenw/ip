package aurafarmer;

import java.util.ArrayList;
import java.util.Scanner;

import aurafarmer.task.Task;

/**
 * Handles interactions with the user through the command line.
 */
public class Ui {
    private static final String LINE = "    ____________________________________________________________";
    private Scanner scanner;
    private StringBuilder responseBuffer;

    /**
     * Constructs a new Ui instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
        this.responseBuffer = new StringBuilder();
    }

    private void print(String message) {
        System.out.println(message);
        responseBuffer.append(message).append("\n");
    }

    /**
     * Returns the last captured response and clears the buffer.
     */
    public String getLastResponse() {
        String response = responseBuffer.toString().trim();
        responseBuffer.setLength(0);
        return response;
    }

    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        showLine();
        System.out.println("    aura: hello! i'm aurafarmer");
        System.out.println("    aura: what can i do for you?");
        showLine();
    }

    public void showGoodbye() {
        print("    aura: next time you return, return with more aura");
    }

    public void showError(String message) {
        print("    aura: oops! " + message);
    }

    public void showLoadingError() {
        System.out.println("    aura: couldn't load tasks, starting fresh");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays all tasks in the given task list.
     *
     * @param tasks TaskList to display.
     */
    public void showTaskList(TaskList tasks) {
        print("    aura: here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            print("    aura: " + (i + 1) + "." + tasks.getTask(i));
        }
    }

    /**
     * Displays a message confirming a task was added.
     *
     * @param task Task that was added.
     * @param size Current number of tasks in the list.
     */
    public void showTaskAdded(Task task, int size) {
        print("    aura: got it. i've added this task:");
        print("    aura:   " + task);
        print("    aura: now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message confirming a task was deleted.
     *
     * @param task Task that was deleted.
     * @param size Current number of tasks in the list.
     */
    public void showTaskDeleted(Task task, int size) {
        print("    aura: noted. i've removed this task:");
        print("    aura:   " + task);
        print("    aura: now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message confirming a task was marked as done.
     *
     * @param task Task that was marked.
     */
    public void showTaskMarked(Task task) {
        print("    aura: nice! i've marked this task as done:");
        print("    aura:   " + task);
    }

    /**
     * Displays a message confirming a task was marked as not done.
     *
     * @param task Task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        print("    aura: ok, i've marked this task as not done yet:");
        print("    aura:   " + task);
    }

    /**
     * Displays the list of tasks matching a search keyword.
     */
    public void showFoundTasks(ArrayList<Task> tasks) {
        print("    aura: here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            print("    " + (i + 1) + "." + tasks.get(i));
        }
    }

    /**
     * Displays the task list after sorting.
     */
    public void showSorted(TaskList tasks) {
        print("    aura: tasks sorted by date:");
        for (int i = 0; i < tasks.size(); i++) {
            print("    aura: " + (i + 1) + "." + tasks.getTask(i));
        }
    }

    public void close() {
        scanner.close();
    }
}
