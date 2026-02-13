package aurafarmer;

import java.util.ArrayList;

import aurafarmer.task.Task;

/**
 * Represents a list of tasks and provides operations to modify it.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks Initial list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the given index.
     *
     * @param index Zero-based index of the task to delete.
     * @return The deleted task.
     * @throws AuraFarmerException If the index is out of range.
     */
    public Task deleteTask(int index) throws AuraFarmerException {
        if (index < 0 || index >= tasks.size()) {
            throw new AuraFarmerException("that task doesn't exist, no aura for you");
        }
        return tasks.remove(index);
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index Zero-based index of the task to mark.
     * @return The marked task.
     * @throws AuraFarmerException If the index is out of range.
     */
    public Task markTask(int index) throws AuraFarmerException {
        if (index < 0 || index >= tasks.size()) {
            throw new AuraFarmerException("that task doesn't exist, no aura for you");
        }
        tasks.get(index).markAsDone();
        return tasks.get(index);
    }

    /**
     * Marks the task at the given index as not done.
     *
     * @param index Zero-based index of the task to unmark.
     * @return The unmarked task.
     * @throws AuraFarmerException If the index is out of range.
     */
    public Task unmarkTask(int index) throws AuraFarmerException {
        if (index < 0 || index >= tasks.size()) {
            throw new AuraFarmerException("that task doesn't exist, no aura for you");
        }
        tasks.get(index).markAsNotDone();
        return tasks.get(index);
    }

    /**
     * Returns the task at the given index.
     *
     * @param index Zero-based index of the task.
     * @return The task at the given index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
