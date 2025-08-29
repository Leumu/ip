/**
 * This class is a subclass from task
 * Works like a normal task without deadlines
 */
public class Todo extends Task {

    /**
     * This is my to do constructor
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Provides info of the to do task in string format
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String getExtraDetailsForStorage() {
        return ""; // nothing extra
    }
}
