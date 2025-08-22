/**
 * This class is a subclass from task
 * A task with a starting date and an end date
 */
public class Event extends Task {
    private String starting;
    private String end;

    public Event(String taskName, String starting, String end) {
        super(taskName);
        this.starting = starting;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), starting, end);
    }
}
