/**
 * This class is a subclass from task
 * A task with a deadline
 */
public class Deadline extends Task {
    private String dueDate;

    /**
     * This is my deadline constructor
     */
    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }

    /**
     * Provides info of the deadline task in string format
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), dueDate);
    }

    /**
     * Returns the dueDate
     */
    @Override
    public String getExtraDetails() {
        return dueDate; // nothing extra
    }
}
