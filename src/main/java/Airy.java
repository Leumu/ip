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
                    System.out.printf("%d.[%s][%s] %s %s\n",
                            i + 1,
                            taskObj.getType(),
                            taskObj.getStatus(),
                            taskObj.getTaskName(),
                            taskObj.getExtraDetails());
                }
                System.out.print("\n");
            } else if (lower.startsWith("mark ")) {
                // Gets the number after mark, -1 cause array is 0 indexed
                int taskNum = Integer.parseInt(lower.substring(5)) - 1;
                Task taskObj = tasks[taskNum];
                taskObj.markCompleted();
                System.out.println("Nice! I've marked this task as done:");
                System.out.printf("[%s][%s] %s\n\n",
                        taskObj.getType(),
                        taskObj.getStatus(),
                        taskObj.getTaskName());
            } else if (lower.startsWith("unmark ")) {
                // Gets the number after unmark, -1 cause array is 0 indexed
                int taskNum = Integer.parseInt(lower.substring(7)) - 1;
                Task taskObj = tasks[taskNum];
                taskObj.markUncompleted();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.printf("[%s][%s] %s\n\n",
                        taskObj.getType(),
                        taskObj.getStatus(),
                        taskObj.getTaskName());
            } else if (lower.startsWith("todo ")){
                String taskName = repeat.substring(5);
                // New to do object
                Todo todoTask = new Todo(taskName);
                // Save to do object into array
                tasks[increment] = todoTask;
                increment++;
                // Show user task has been added
                System.out.println("Got it. I've added this task:");
                System.out.printf("  [%s][ ] %s\n",
                        todoTask.getType(),
                        todoTask.getTaskName());
                System.out.printf("Now you have %d tasks in the list.\n\n", increment);
            } else if (lower.startsWith("deadline ")){
                String[] parts = repeat.substring(9).split("/by");
                if (parts.length == 2) {
                    String taskName = parts[0].trim();
                    String dueDate = parts[1].trim();
                    // New deadline object
                    Deadline deadlineTask = new Deadline(taskName, dueDate);
                    // Save Deadline object into array
                    tasks[increment] = deadlineTask;
                    increment++;
                    // Show user task has been added
                    System.out.println("Got it. I've added this task:");
                    System.out.printf("  [%s][ ] %s %s\n",
                            deadlineTask.getType(),
                            deadlineTask.getTaskName(),
                            deadlineTask.getExtraDetails());
                    System.out.printf("Now you have %d tasks in the list.\n\n", increment);
                }
            } else if (lower.startsWith("event ")){
                String[] parts = repeat.substring(6).split("/from|/to");
                if (parts.length == 3) {
                    String taskName = parts[0].trim();
                    String starting = parts[1].trim();
                    String end = parts[2].trim();
                    // New event object
                    Event eventTask = new Event(taskName, starting, end);
                    // Save Event object into array
                    tasks[increment] = eventTask;
                    increment++;
                    // Show user task has been added
                    System.out.println("Got it. I've added this task:");
                    System.out.printf("  [%s][ ] %s %s\n",
                            eventTask.getType(),
                            eventTask.getTaskName(),
                            eventTask.getExtraDetails());
                    System.out.printf("Now you have %d tasks in the list.\n\n", increment);
                }
            }
        } while (true);

        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
