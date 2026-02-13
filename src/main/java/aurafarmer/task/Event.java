package aurafarmer.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that spans a time period with start and end dates.
 */
public class Event extends Task {

    protected LocalDate fromDate;
    protected LocalDate toDate;
    protected String fromString;
    protected String toStringVal;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Constructs a new Event with the given description, start date, and end date.
     * Dates in yyyy-MM-dd format are parsed as LocalDate; otherwise stored as strings.
     *
     * @param description Description of the event.
     * @param from Start date string.
     * @param to End date string.
     */
    public Event(String description, String from, String to) {
        super(description);
        try {
            this.fromDate = LocalDate.parse(from, INPUT_FORMAT);
            this.fromString = null;
        } catch (DateTimeParseException e) {
            this.fromDate = null;
            this.fromString = from;
        }
        try {
            this.toDate = LocalDate.parse(to, INPUT_FORMAT);
            this.toStringVal = null;
        } catch (DateTimeParseException e) {
            this.toDate = null;
            this.toStringVal = to;
        }
    }

    /**
     * Returns the start date in yyyy-MM-dd format for storage.
     *
     * @return Start date string in input format.
     */
    public String getFrom() {
        if (fromDate != null) {
            return fromDate.format(INPUT_FORMAT);
        }
        return fromString;
    }

    /**
     * Returns the end date in yyyy-MM-dd format for storage.
     *
     * @return End date string in input format.
     */
    public String getTo() {
        if (toDate != null) {
            return toDate.format(INPUT_FORMAT);
        }
        return toStringVal;
    }

    /**
     * Returns the start date in a human-readable format for display.
     *
     * @return Start date string in display format.
     */
    public String getFromForDisplay() {
        if (fromDate != null) {
            return fromDate.format(OUTPUT_FORMAT);
        }
        return fromString;
    }

    /**
     * Returns the end date in a human-readable format for display.
     *
     * @return End date string in display format.
     */
    public String getToForDisplay() {
        if (toDate != null) {
            return toDate.format(OUTPUT_FORMAT);
        }
        return toStringVal;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFromForDisplay() + " to: " + getToForDisplay() + ")";
    }
}
