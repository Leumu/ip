import java.util.ArrayList;

/**
 * This class holds the task ArrayList
 * Handles logic for the ArrayList
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor that takes in an ArrayList from Storage.load()
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to this ArrayList
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task to this ArrayList
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks a task in ArrayList
     */
    public Task markTask(int index) {
        Task t = tasks.get(index);
        t.markCompleted();
        return t;
    }

    /**
     * Unmarks a task in ArrayList
     */
    public Task unmarkTask(int index) {
        Task t = tasks.get(index);
        t.markUncompleted();
        return t;
    }

    /**
     * Get size of this ArrayList
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Fetches this ArrayList
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
