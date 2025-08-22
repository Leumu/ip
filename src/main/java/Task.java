/**
 * Task object to keep track of task name and status
 */
public class Task {
    private String taskName;
    private Boolean isCompleted;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    public String getStatus() {
        return (isCompleted ? "X" : " ");
    }

    public void markCompleted() {
        isCompleted = true;
    }

    public void markUncompleted() {
        isCompleted = false;
    }

    public String getTaskName() {
        return taskName;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatus(), getTaskName());
    }
}