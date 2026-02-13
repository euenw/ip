package aurafarmer.task;

/**
 * Represents a todo task with no date attached.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo with the given description.
     *
     * @param description Description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
