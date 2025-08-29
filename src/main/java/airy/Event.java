package airy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * This class is a subclass from task
 * A task with a starting date and an end date
 */
public class Event extends Task {
    private final LocalDate startDate;
    private final LocalDate endDate;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * This is my event constructor
     */
    public Event(String taskName, String startDate, String endDate) {
        super(taskName);
        try {
            this.startDate = LocalDate.parse(startDate, INPUT_FORMAT);
            this.endDate = LocalDate.parse(endDate, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new AiryException("Invalid date format, please use yyyy-MM-dd, e.g. 2025-06-02");
        }
    }

    /**
     * Provides info of the event task in string format
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(),
                startDate.format(OUTPUT_FORMAT),
                endDate.format(OUTPUT_FORMAT));
    }

    /**
     * Returns the startDate and endDate in input format for Storage
     */
    @Override
    public String getExtraDetailsForStorage() {
        return startDate.format(INPUT_FORMAT) + " | " + endDate.format(INPUT_FORMAT);
    }
}
