/**
 * Task object to keep track of task name and status
 */
public abstract class Task {
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

    public abstract String getType();

    public abstract String getExtraDetails();
}