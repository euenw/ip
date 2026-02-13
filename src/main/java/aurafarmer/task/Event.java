package aurafarmer.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

    protected LocalDate fromDate;
    protected LocalDate toDate;
    protected String fromString;
    protected String toStringVal;

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

    public String getFrom() {
        if (fromDate != null) {
            return fromDate.format(INPUT_FORMAT);
        }
        return fromString;
    }

    public String getTo() {
        if (toDate != null) {
            return toDate.format(INPUT_FORMAT);
        }
        return toStringVal;
    }

    public String getFromForDisplay() {
        if (fromDate != null) {
            return fromDate.format(OUTPUT_FORMAT);
        }
        return fromString;
    }

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
