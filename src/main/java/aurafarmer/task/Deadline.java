package aurafarmer.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline date.
 */
public class Deadline extends Task {

    protected LocalDate by;
    protected String byString;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Constructs a new Deadline with the given description and due date.
     * If the date string is in yyyy-MM-dd format, it is parsed as a LocalDate.
     * Otherwise, it is stored as a plain string.
     *
     * @param description Description of the deadline task.
     * @param by Due date string.
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDate.parse(by, INPUT_FORMAT);
            this.byString = null;
        } catch (DateTimeParseException e) {
            this.by = null;
            this.byString = by;
        }
    }

    /**
     * Returns the due date in yyyy-MM-dd format for storage.
     *
     * @return Due date string in input format.
     */
    public String getBy() {
        if (by != null) {
            return by.format(INPUT_FORMAT);
        }
        return byString;
    }

    /**
     * Returns the due date in a human-readable format for display.
     *
     * @return Due date string in display format.
     */
    public String getByForDisplay() {
        if (by != null) {
            return by.format(OUTPUT_FORMAT);
        }
        return byString;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getByForDisplay() + ")";
    }
}
