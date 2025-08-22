/**
 * This class is a subclass from task
 * Works like a normal task without deadlines
 */
public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String getExtraDetails() {
        return "";
    }
}
