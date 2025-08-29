package airy;

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

    /**
     * Returns the number of tasks in the ArrayList.
     *
     * @return the number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified index in the ArrayList.
     *
     * @param index the index of the task to return
     * @return the task at the specified index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the list contains no tasks
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Finds all tasks that contain the given keyword in their description.
     *
     * @param searchInput the search term to look for in task name
     * @return a new TaskList containing only the tasks that match the search criteria
     */
    public TaskList findTasks(String searchInput) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        String lowerSearchInput = searchInput.toLowerCase();

        // Iterate over whole tasks ArrayList to see if any match the search input
        for (Task task : tasks) {
            if (task.getTaskName().toLowerCase().contains(lowerSearchInput)) {
                foundTasks.add(task);
            }
        }
        return new TaskList(foundTasks);
    }
}
