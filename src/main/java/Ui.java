import java.util.ArrayList;

/**
 * This class handles all UI methods the bot has
 */
public class Ui {
    String name = "Airy";

    public void welcomeMessage() {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?\n");
    }

    public void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

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
}
