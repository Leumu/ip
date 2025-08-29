/**
 * This class is a subclass from task
 * A task with a starting date and an end date
 */
public class Event extends Task {
    private String startDate;
    private String endDate;

    /**
     * This is my event constructor
     */
    public Event(String taskName, String startDate, String endDate) {
        super(taskName);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Provides info of the event task in string format
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), startDate, endDate);
    }

    /**
     * Returns the starting and end
     */
    @Override
    public String getExtraDetails() {
        return startDate + " | " + endDate; // nothing extra
    }
}
