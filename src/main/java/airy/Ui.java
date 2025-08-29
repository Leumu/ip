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
     * Shows list of tasks
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
     * Shows task created confirmation
     */
    public void showTaskAdded(Task task, int total) {
        System.out.println("Got it. I've added this task:");
        System.out.printf("  %s\n", task);
        System.out.printf("Now you have %d tasks in the list.\n\n", total);
    }

    /**
     * Shows task removed confirmation
     */
    public void showTaskRemoved(Task task, int total) {
        System.out.println("Noted. I've removed this task:");
        System.out.printf("  %s\n", task);
        System.out.printf("Now you have %d tasks in the list.\n\n", total);
    }

    /**
     * Shows task as marked confirmation
     */
    public void showMark(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task + "\n");
    }

    /**
     * Shows task as unmarked confirmation
     */
    public void showUnmark(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task + "\n");
    }
}
