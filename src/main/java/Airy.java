import java.util.Scanner;

public class Airy {
    public static void main(String[] args) {
        String name = "Airy";
        String repeat;
        String lower;

        // Detect input
        Scanner sc = new Scanner(System.in);
        // To store Task objects
        Task[] tasks = new Task[100];
        // To take note of increment for the tasks array
        int increment = 0;

        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?\n");

        do {
            repeat = sc.nextLine();
            lower = repeat.toLowerCase();

            // Only repeat if it's not bye
            if (lower.equals("bye")) {
                break;
            } else if (lower.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                // Print out saved tasks
                for (int i = 0; i < increment; i++) {
                    Task taskObj = tasks[i];
                    System.out.printf("%d.[%s] %s\n", i + 1, taskObj.getStatus(), taskObj.getTaskName());
                }
                System.out.print("\n");
            } else if (lower.startsWith("mark ")) {
                // Gets the number after mark, -1 cause array is 0 indexed
                int taskNum = Integer.parseInt(lower.substring(5)) - 1;
                Task taskObj = tasks[taskNum];
                taskObj.markCompleted();
                System.out.println("Nice! I've marked this task as done:");
                System.out.printf("[%s] %s\n\n", taskObj.getStatus(), taskObj.getTaskName());
            } else if (lower.startsWith("unmark ")) {
                // Gets the number after unmark, -1 cause array is 0 indexed
                int taskNum = Integer.parseInt(lower.substring(7)) - 1;
                Task taskObj = tasks[taskNum];
                taskObj.markUncompleted();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.printf("[%s] %s\n\n", taskObj.getStatus(), taskObj.getTaskName());
            } else {
                // Save Task object into array
                tasks[increment] = new Task(repeat);
                increment++;
                // Show user task has been added
                System.out.printf("added: %s\n\n", repeat);
            }
        } while (true);

        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
