package aurafarmer;

import java.util.Scanner;

import aurafarmer.task.Task;

/**
 * Handles interactions with the user through the command line.
 */
public class Ui {
    private static final String LINE = "    ____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showWelcome() {
        showLine();
        System.out.println("    aura: hello! i'm aurafarmer");
        System.out.println("    aura: what can i do for you?");
        showLine();
    }

    public void showGoodbye() {
        System.out.println("    aura: next time you return, return with more aura");
    }

    public void showError(String message) {
        System.out.println("    aura: oops! " + message);
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
        System.out.println("    aura: here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    aura: " + (i + 1) + "." + tasks.getTask(i));
        }
    }

    /**
     * Displays a message confirming a task was added.
     *
     * @param task Task that was added.
     * @param size Current number of tasks in the list.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("    aura: got it. i've added this task:");
        System.out.println("    aura:   " + task);
        System.out.println("    aura: now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message confirming a task was deleted.
     *
     * @param task Task that was deleted.
     * @param size Current number of tasks in the list.
     */
    public void showTaskDeleted(Task task, int size) {
        System.out.println("    aura: noted. i've removed this task:");
        System.out.println("    aura:   " + task);
        System.out.println("    aura: now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message confirming a task was marked as done.
     *
     * @param task Task that was marked.
     */
    public void showTaskMarked(Task task) {
        System.out.println("    aura: nice! i've marked this task as done:");
        System.out.println("    aura:   " + task);
    }

    /**
     * Displays a message confirming a task was marked as not done.
     *
     * @param task Task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("    aura: ok, i've marked this task as not done yet:");
        System.out.println("    aura:   " + task);
    }

    public void close() {
        scanner.close();
    }
}
