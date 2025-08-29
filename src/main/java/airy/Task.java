package airy;

/**
 * Task object to keep track of task name and status
 */
public abstract class Task {
    private final String taskName;
    private Boolean isCompleted;

    /**
     * This is my task constructor
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    /**
     * Fetches the status of the task and returns a String
     */
    public String getStatus() {
        return (isCompleted ? "X" : " ");
    }

    /**
     * Marks the task as completed
     */
    public void markCompleted() {
        isCompleted = true;
    }

    /**
     * Marks the task as uncompleted
     */
    public void markUncompleted() {
        isCompleted = false;
    }

    /**
     * Fetches the name of the task
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Provides general info of the task in string format
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatus(), getTaskName());
    }

    /**
     * Gets extra details, esp for Tasks like Deadline and Event for Storage class
     */
    public abstract String getExtraDetailsForStorage();
}