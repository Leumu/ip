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
     *
     * @param tasks the initial collection of tasks to manage
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to this ArrayList
     *
     * @param task the task object to add to the ArrayList
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task to this ArrayList
     *
     * @param index the index of the task to remove
     * @return the task that was removed from the ArrayList
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks a task in ArrayList
     *
     * @param index mark task as completed
     * @return the task that was marked as completed
     */
    public Task markTask(int index) {
        Task t = tasks.get(index);
        t.markCompleted();
        return t;
    }

    /**
     * Unmarks a task in ArrayList
     *
     * @param index mark task as not completed
     * @return the task that was marked as not completed
     */
    public Task unmarkTask(int index) {
        Task t = tasks.get(index);
        t.markUncompleted();
        return t;
    }

    /**
     * Get size of this ArrayList
     *
     * @return the number of tasks in the collection
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Fetches this ArrayList
     *
     * @return the ArrayList containing all managed tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
