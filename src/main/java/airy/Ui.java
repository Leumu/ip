package airy;

import java.util.ArrayList;

/**
 * This class handles all UI methods the bot has
 */
public class Ui {
    String name = "Airy";

    /**
     * Sends welcome message
     */
    public void welcomeMessage() {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?\n");
    }

    /**
     * Sends bye message
     */
    public void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the complete list of tasks to the user.
     *
     * @param tasks the ArrayList of tasks to display
     */
    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        // Print out saved tasks
        for (int i = 0; i < tasks.size(); i++) {
            Task taskObj = tasks.get(i);
            System.out.printf("%d. %s\n",
                    i + 1,
                    taskObj.toString());
        }
        System.out.print("\n");
    }

    /**
     * Confirms that a task has been successfully added to the list.
     * Shows the added task and updates the user on the total task count.
     *
     * @param task the task that was added
     * @param total the new total number of tasks in the list
     */
    public void showTaskAdded(Task task, int total) {
        System.out.println("Got it. I've added this task:");
        System.out.printf("  %s\n", task);
        System.out.printf("Now you have %d tasks in the list.\n\n", total);
    }

    /**
     * Confirms that a task has been successfully removed from the list.
     * Shows the removed task and updates the user on the total task count.
     *
     * @param task the task that was removed
     * @param total the new total number of tasks in the list
     */
    public void showTaskRemoved(Task task, int total) {
        System.out.println("Noted. I've removed this task:");
        System.out.printf("  %s\n", task);
        System.out.printf("Now you have %d tasks in the list.\n\n", total);
    }

    /**
     * Confirms that a task has been marked as completed.
     *
     * @param task the task that is to be marked as done
     */
    public void showMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task + "\n");
    }

    /**
     * Confirms that a task has been marked as not completed.
     *
     * @param task the task that is to be marked as not done
     */
    public void showUnmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task + "\n");
    }

    /**
     * Displays the list of tasks that match the search criteria.
     * Displays a message if no matching tasks are found.
     *
     * @param matchingTasks the list of tasks that match the search
     */
    public void showMatchingTasks(TaskList matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list::");
            for (int i = 0; i < matchingTasks.size(); i++) {
                Task taskObj = matchingTasks.get(i);
                System.out.printf("%d. %s\n", i + 1, taskObj.toString());
            }
        }
        System.out.print("\n");
    }
}
