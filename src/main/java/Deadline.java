/**
 * This class is a subclass from task
 * A task with a deadline
 */
public class Deadline extends Task {
    private String dueDate;

    public Deadline(String taskName, String dueDate) {
        super(taskName);
        this.dueDate = dueDate;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getExtraDetails() {
        return "(by: " + dueDate + ")";
    }
}
