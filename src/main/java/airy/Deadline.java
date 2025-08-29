package airy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class is a subclass from task
 * A task with a deadline
 */
public class Deadline extends Task {
    private final LocalDate dueDate;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * This is my deadline constructor
     */
    public Deadline(String taskName, String dueDate) {
        super(taskName);
        try {
            this.dueDate = LocalDate.parse(dueDate, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new AiryException("Invalid date format, please use yyyy-MM-dd, e.g. 2025-06-02");
        }
    }

    /**
     * Provides info of the deadline task in string format
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                dueDate.format(OUTPUT_FORMAT));
    }

    /**
     * Returns the dueDate in input format for Storage
     */
    @Override
    public String getExtraDetailsForStorage() {
        return dueDate.format(INPUT_FORMAT);
    }
}
