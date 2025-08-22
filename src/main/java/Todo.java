/**
 * This class is a subclass from task
 * Works like a normal task without deadlines
 */
public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
