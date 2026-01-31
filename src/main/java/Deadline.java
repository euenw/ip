import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDate by;
    protected String byString;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");

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

    public String getBy() {
        if (by != null) {
            return by.format(INPUT_FORMAT);
        }
        return byString;
    }

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
